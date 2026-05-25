package vn.huuchuong.pabackbyhuuchuong.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.huuchuong.pabackbyhuuchuong.entity.Product;
import vn.huuchuong.pabackbyhuuchuong.entity.ProductVariant;
import vn.huuchuong.pabackbyhuuchuong.exception.BusinessException;
import vn.huuchuong.pabackbyhuuchuong.payload.request.CreateProductRequest;
import vn.huuchuong.pabackbyhuuchuong.payload.request.CreateProductVariantRequest;
import vn.huuchuong.pabackbyhuuchuong.payload.request.UpdateProductRequest;
import vn.huuchuong.pabackbyhuuchuong.repository.ProductRepository;
import vn.huuchuong.pabackbyhuuchuong.repository.ProductVariantRepository;
import vn.huuchuong.pabackbyhuuchuong.service.ProductService;
import vn.huuchuong.pabackbyhuuchuong.service.ProductVariantService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantRepository productVariantVariantRepository;

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Boolean createProduct(CreateProductRequest createProductRequest) {
        // validate

        // ten co trung ko
        if (productRepository.existsByName(createProductRequest.getName())){
            throw new BusinessException("Sản phẩm đã bị trùng với 1 sản phẩm khác");
        }
        // ktar do dai va null da o trong dto

        // save
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setLinkImg(createProductRequest.getLinkImg());

        List<ProductVariant> variants = new ArrayList<>();
        for (CreateProductVariantRequest variantRequest : createProductRequest.getVariants()) {
            ProductVariant productVariant = new ProductVariant();
            productVariant.setSize(variantRequest.getSize());
            productVariant.setPrice(variantRequest.getPrice());
            productVariant.setQuantity(variantRequest.getQuantity());
            productVariant.setColor(variantRequest.getColor());
            productVariant.setProduct(product);
            variants.add(productVariant);

        }
        product.setVariants(variants);
        productRepository.save(product);
        return true;




    }

    @Override
    public Product getDetailProduct(int id) {
        // Kiem tra id co ton tai ko
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Sản phẩm không tồn tại"));

        //  lay cac thong tin varriant
        List<ProductVariant> variants = productVariantRepository.findByProductId(id);
        product.setVariants(variants);
        return product;

    }

    @Override
    public Boolean update(int id, UpdateProductRequest req) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()){
            throw new BusinessException("Sản phẩm không tồn tại");
        }
        // name
        if (req.getName() != null && !req.getName().isBlank()) {
            String cleaned = req.getName().trim();
            if (cleaned.length() < 3) {
                throw new BusinessException("Tên sản phẩm phải có ít nhất 3 ký tự");
            }
            product.get().setName(cleaned);
        }

        // description
        if (req.getDescription() != null && !req.getDescription().isBlank()) {
            product.get().setDescription(req.getDescription().trim());
        }

        // Lin Anh
        if (req.getLinkImg() != null && !req.getLinkImg().isBlank()) {
            product.get().setLinkImg(req.getLinkImg().trim());
        }

        productRepository.save(product.get());
        return true;




    }

    @Override
    public Boolean delete(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()){
            throw new BusinessException("Product không tồn tại");

        }

        List<ProductVariant> productVarDel = productVariantRepository.findAllByProductId(id);

        productVariantRepository.deleteAll(productVarDel);
        productRepository.delete(product.get());
        return true;
    }
}
