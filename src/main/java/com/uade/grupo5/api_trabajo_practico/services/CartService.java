package com.uade.grupo5.api_trabajo_practico.services;

import com.uade.grupo5.api_trabajo_practico.dto.ProductDTO;
import com.uade.grupo5.api_trabajo_practico.exceptions.CartException;
import com.uade.grupo5.api_trabajo_practico.exceptions.ProductException;
import com.uade.grupo5.api_trabajo_practico.repositories.CartRepository;
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
  private ProductService productService;
  @Autowired
  private BuyService buyService;

  // ** SIRVE **
  public Cart createCart() throws Exception {
    try {
      Cart cart = new Cart();
      return cart;
    } catch (Exception error) {
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
  public Item addProductToCart(ProductDTO productDTO, Long cartId) throws Exception {
    try {
      Product product = productDTO.toEntity();

      Cart cart = getCartById(cartId);

      Item item = cart.getItems().stream()
          .filter(cartItem -> cartItem.getProductId().equals(product.getId()))
          .findFirst()
          .orElse(null);

      if (item != null) {
        // Si ya existe, actualizamos la cantidad
        if (product.getStock() < item.getQuantity() + 1) {
          throw new ProductException("No hay stock suficiente del producto elegido");
        } else {
          item.setQuantity(item.getQuantity() + 1);
        }

      } else {
        // Si no existe, creamos un nuevo ítem
        item = new Item();
        item.setProduct(product);
        item.setQuantity(1);
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
  public void removeProductFromCart(Long cartId, Long productId) throws Exception {
    try {
      Cart cart = getCartById(cartId);

      Product product = productService.getProductById(productId);

      Item item = cart.getItems().stream()
          .filter(cartItem -> cartItem.getProductId().equals(product.getId()))
          .findFirst()
          .orElse(null);

      if (item != null) {
        if (item.getQuantity() == 1) {
          cart.getItems().remove(item);
        } else {
          item.setQuantity(item.getQuantity() - 1);
        }
      }

      cartRepository.save(cart);
    } catch (CartException error) {
      throw new CartException(error.getMessage());
    } catch (Exception error) {
      throw new Exception("[CartService.removeItemFromCart] -> " + error.getMessage());
    }
  }

  // ** SIRVE **
  @Transactional
  public void removeItemFromCart(Long cartId, Long productId) throws Exception {
    try {
      Cart cart = getCartById(cartId);

      Product product = productService.getProductById(productId);

      Item item = cart.getItems().stream()
          .filter(cartItem -> cartItem.getProductId().equals(product.getId()))
          .findFirst()
          .orElse(null);

      cart.getItems().remove(item);

      cartRepository.save(cart);
    } catch (CartException error) {
      throw new CartException(error.getMessage());
    } catch (Exception error) {
      throw new Exception("[CartService.removeItemFromCart] -> " + error.getMessage());
    }
  }

  // ** SIRVE **
  public void emptyCart(Long cartId) throws Exception {
    try {
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
    try {
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
