package ru.orchidaily.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.orchidaily.web.data.ProductData;
import ru.orchidaily.web.data.transfer.ProductDataTransfer;
import ru.orchidaily.web.data.transfer.ProductListDataTransfer;
import ru.orchidaily.web.service.ProductService;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 10:52 PM
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    final String productList(final @RequestParam(required = false, defaultValue = "0") int page,
                             final @RequestParam(required = false, defaultValue = "20") int size,
                             final Model model) {
        ProductListDataTransfer productDto = productService.getProducts(page, size);
        model.addAttribute("page", productDto);
        return "list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    final String product(final @PathVariable String id, final Model model) {
        ProductDataTransfer productDto = productService.getProductById(id);
        model.addAttribute("product", productDto.getProduct());
        return "view";
    }
}
