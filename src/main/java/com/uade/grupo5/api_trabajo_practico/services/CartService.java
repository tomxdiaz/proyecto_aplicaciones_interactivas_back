package com.uade.grupo5.api_trabajo_practico.services;

import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import com.uade.grupo5.api_trabajo_practico.exceptions.CartException;
import com.uade.grupo5.api_trabajo_practico.exceptions.ProductException;
import com.uade.grupo5.api_trabajo_practico.repositories.CartRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.ProductRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Buy;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BuyService buyService;

    // ** SIRVE **
    public Cart createCart() throws Exception{
      try{
          Cart cart = new Cart();
        return cart;
      }catch(Exception error){
        throw new Exception("[CartService.createCart] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    public Cart getCartById(Long cartId) throws Exception {
      try {
        return cartRepository.findById(cartId)
              .orElseThrow(() -> new CartException("No se encontro el carro."));
      } catch (CartException error) {
        throw new CartException(error.getMessage());
      } catch (Exception error) {
        throw new Exception("[CartService.getCartById] -> " + error.getMessage());
      } 
    }

    // ** SIRVE **
    @Transactional
    public Item addItemToCart(ItemDTO itemDTO, Long cartId) throws Exception {
      try{
        if (itemDTO.getQuantity() < 0)  // Deberia siempre ser filtrado por el front
          throw new CartException("La cantidad no puede ser negativa");
        
        // Obtener el producto por ID
        Product product = productRepository.findById(itemDTO.getProduct().getId())
                .orElseThrow(() -> new ProductException("Producto no encontrado"));
        if (product.getStock() < itemDTO.getQuantity()) {
            throw new ProductException("No hay stock suficiente del producto elegido");
        }

        Cart cart = getCartById(cartId);

        // Buscar el ítem en la lista de ítems del carrito
        Item item = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProductId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (item != null) {
            // Si ya existe, actualizamos la cantidad
            item.setQuantity(itemDTO.getQuantity());

        } else {
            // Si no existe, creamos un nuevo ítem
            item = new Item();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setCart(cart);

            // Agregamos el nuevo ítem al carrito
            cart.getItems().add(item);
        }

        // Guardar el carrito actualizado
        cartRepository.save(cart);
        return item;
      } catch (CartException error) {
        throw new CartException(error.getMessage());
      } catch (ProductException error) {
        throw new ProductException(error.getMessage());
      } catch (Exception error) {
        throw new Exception("[CartService.addItemToCart] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    @Transactional
    public void removeItemFromCart(Long cartId, Long productId) throws Exception {
      try{
        Cart cart = getCartById(cartId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cartRepository.save(cart);
      } catch (CartException error) {
        throw new CartException(error.getMessage());
      } catch (Exception error) {
        throw new Exception("[CartService.removeItemFromCart] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    public void emptyCart(Long cartId) throws Exception {
      try{
        Cart cart = getCartById(cartId);
        cart.getItems().clear();
        cartRepository.save(cart);
      } catch (CartException error) {
        throw new CartException(error.getMessage());
      } catch (Exception error) {
        throw new Exception("[CartService.emptyCart] -> " + error.getMessage());
      }
    }

    // ** SIRVE **
    public Buy checkout(Long cartId) throws Exception {
      try{
        Cart cart = getCartById(cartId);
        Buy buy = buyService.createBuy(cart);
        emptyCart(cartId);
        return buy;
      } catch (CartException error) {
        throw new CartException(error.getMessage());
      } catch (Exception error) {
        throw new Exception("[CartService.checkout] -> " + error.getMessage());
      }
    }
}
