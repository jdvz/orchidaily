package ru.orchidaily.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.orchidaily.core.dao.ProductSolrRepository;
import ru.orchidaily.core.data.SolrProduct;
import ru.orchidaily.core.exception.ProductNotFoundException;
import ru.orchidaily.data.Transfer;
import ru.orchidaily.data.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 10:31 PM
 */
@RestController
@RequestMapping(value = "/product/", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Product API")
public class ProductController {
    @Autowired
    private ProductSolrRepository productSolrRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("retrieve paged products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "returns list of product", response = Page.class),
            @ApiResponse(code = 404, message = "not found")
    })
    public Page<SolrProduct> products(final @RequestParam(required = false, defaultValue = "0") int page,
                                      final @RequestParam(required = false, defaultValue = "20") int size
    ) throws Exception {
        return productSolrRepository.findAll(new SolrPageRequest(page, size));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    @ApiOperation("retrieve product by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "returns product"),
            @ApiResponse(code = 404, message = "not found")
    })
    public ResponseEntity<Transfer<Product>> product(final @RequestParam String id) throws Exception {
        Product product = productSolrRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("product not found for id %s", id)));
        return ResponseEntity.ok(new Transfer<>("ok", product));
    }

    @RequestMapping(method = RequestMethod.POST, params = {"id"})
    @ApiOperation("retrieve product by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "returns product"),
            @ApiResponse(code = 500, message = "error")
    })
    public ResponseEntity<Transfer<Product>> product(final @RequestParam Product product) throws Exception {
        productSolrRepository.save(product);
        return ResponseEntity.ok(new Transfer<>("ok", product));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Transfer<?>> error(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Transfer<>(e.getMessage()));
    }
}
