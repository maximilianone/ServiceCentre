package application.model.entity;

public class Product {
    private int id;
    private String productName;
    private String productType;

    public Product() {
    }

    public Product(Builder builder) {
        this.id = builder.id;
        this.productName = builder.productName;
        this.productType = builder.productType;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        return productType != null ? productType.equals(product.productType) : product.productType == null;
    }

    @Override
    public int hashCode() {
        int result = productName != null ? productName.hashCode() : 0;
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                '}';
    }

    public static class Builder{
        private int id;
        private String productName;
        private String productType;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setProductType(String productType) {
            this.productType = productType;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
