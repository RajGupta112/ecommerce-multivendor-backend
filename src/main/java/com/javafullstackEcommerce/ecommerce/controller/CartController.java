package com.javafullstackEcommerce.ecommerce.controller;

import com.javafullstackEcommerce.ecommerce.Exception.ProductException;
import com.javafullstackEcommerce.ecommerce.modal.Cart;
import com.javafullstackEcommerce.ecommerce.modal.CartItem;
import com.javafullstackEcommerce.ecommerce.modal.Product;
import com.javafullstackEcommerce.ecommerce.modal.User;
import com.javafullstackEcommerce.ecommerce.response.AddItemRequest;
import com.javafullstackEcommerce.ecommerce.response.ApiResponse;
import com.javafullstackEcommerce.ecommerce.service.CartItemService;
import com.javafullstackEcommerce.ecommerce.service.CartService;
import com.javafullstackEcommerce.ecommerce.service.ProductService;
import com.javafullstackEcommerce.ecommerce.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;
    private final CartItemService cartItemService;



    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);

        Cart cart=cartService.findUserCart(user);

        System.out.println("cart - "+cart.getUser().getEmail());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws ExecutionControl.UserException, Exception {

        User user=userService.findUserByJwtToken(jwt);
        Product product=productService.findProductById(req.getProductId());

        CartItem item = cartService.addCartItem(user,
                product,
                req.getSize(),
                req.getQuantity());
ApiResponse res= new ApiResponse();
res.setMessage("Item Added to cart Successfully");

        return new ResponseEntity<>(item,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization")String jwt)
            throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res=new ApiResponse("Item Remove From Cart",true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem>updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization")String jwt)
            throws Exception {

        User user=userService.findUserByJwtToken(jwt);

        CartItem updatedCartItem = null;
        if(cartItem.getQuantity()>0){
            updatedCartItem=cartItemService.updateCartItem(user.getId(),
                    cartItemId, cartItem);
        }




        return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED);
    }

}
