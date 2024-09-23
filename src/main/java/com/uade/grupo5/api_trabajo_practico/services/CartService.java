package com.uade.grupo5.api_trabajo_practico.services;

import com.uade.grupo5.api_trabajo_practico.dto.ItemDTO;
import com.uade.grupo5.api_trabajo_practico.repositories.CartRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.ProductRepository;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Cart;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Item;
import com.uade.grupo5.api_trabajo_practico.repositories.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public void createCart(Long userID) {
        if (userID == null) {
            throw new IllegalArgumentException("userID cannot be null");
        }

        Cart newCart = new Cart();
        newCart.setUserID(userID);
        cartRepository.save(newCart);
    }

    public Cart getCartById(Long cartId) throws Exception{
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }


    public Item addItemToCart(Long cartId, ItemDTO itemDTO) throws Exception{
        // Obtener el carrito por ID
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Crear el nuevo ítem
        Item item = new Item();
        item.setProduct(product);
        item.setQuantity(itemDTO.getQuantity());
        item.setCart(cart);

        // Agregar el ítem al carrito
        cart.getItems().add(item);

        // Guardar el carrito actualizado
        cartRepository.save(cart);
        return item;
    }

    public List<Item> getItemsByCart(Long cartId) throws Exception{
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found")).getItems();

    }
    public double getItemSubtotal(Long cartId, int itemId) {
        Item item = cartRepository.findById(cartId).get().getItems().get(itemId);
        return item.getQuantity() * item.getProduct().getPrice();
    }

    public double getTotal(Long cartId) {
        List<Item> products = cartRepository.findById(cartId).get().getItems();
        double total = 0;
        for (Item item : products) {
            total += item.getQuantity() * item.getProduct().getPrice();
        }
        return total;
    }




    public void removeItemFromCart(Long cartId, Long productId) throws Exception{
        Cart cart = getCartById(cartId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cartRepository.save(cart);
    }

    public void emptyCart(Long cartId) {
        Cart cart = getCartById(cartId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    public void removeCart(Long cartId) throws Exception{
        cartRepository.deleteById(cartId);
    }


}
