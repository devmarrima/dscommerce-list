# DSCommerce-List

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](./LICENSE) para mais detalhes.

![MIT License](https://img.shields.io/npm/l/react)


Projeto de uma API RESTful para gerenciamento de produtos e pedidos em um sistema de e-commerce. Desenvolvido utilizando **Java 21** e **Spring Boot 3**, seguindo boas práticas de arquitetura, modelagem de domínio e segurança.

---

## Índice

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Modelagem de Domínio](#modelagem-de-domínio)
- [Operações CRUD](#operações-crud)
- [Autenticação e Autorização](#autenticação-e-autorização)
- [Consultas Personalizadas](#consultas-personalizadas)
- [Como Rodar o Projeto](#como-rodar-o-projeto)
- [Observações](#observações)

---

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security (JWT)
- Banco de Dados H2
- Maven

---

## Modelagem de Domínio


**Entidades Principais:**

- Product (Produto)
- Category (Categoria)
- User (Usuário)
- Order (Pedido)
- OrderItem (Item do Pedido)
- Payment (Pagamento)

**Enumerações:**

- OrderStatus (Status do Pedido)

**Relacionamentos:**

-Product ↔ Category (@ManyToMany)
-Order ↔ User (@ManyToOne)
-Order ↔ Payment (@OneToOne)
-Order ↔ Product (atravez do OrderItem)

![Diagrama de Entidades Relacionadas](imagens/M-relacional.png)

> 

##Classes:

#Product
```java
@Entity
@Table(name = "tb_product")
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	private Double price;
	private String imgUrl;
	
	@ManyToMany
	@JoinTable(name = "tb_product_category",
	        joinColumns = @JoinColumn(name = "product_id"),
	        inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "id.product")
	public Set<OrderItem> items = new HashSet<>();
}
```

#Category
```java
@Entity
@Table(name = "tb_category")
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	private Set<Product> product = new HashSet<>();
	
}


```

#Order
```java
@Entity
@Table(name = "tb_order")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant moment;
	
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	@OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
	private Payment payment;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();

}

```

#User
```java
@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;
	private String phone;
	private LocalDate birthDate;
	private String password;

	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

}

```

#Payment
```java
@Entity
@Table(name = "tb_payment")
public class Payment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant moment;
	
	@OneToOne
	@MapsId
	private Order order;

}

```

#OrderItem
```java
@Entity
@ Table(name = "tb_orderItem")
public class OrderItem {
	
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
		
	}

	public OrderItem(Order order,Product product, Integer quantity, Double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
}

```

#OrderItemPK
```java
@Embeddable
public class OrderItemPK {

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
}

```
---

## Operações CRUD

**Endpoints principais:**

- `/products`
- `/categories`
- `/orders`
- `/users`

### Exemplo: Criar Produto

```json
{
  "name": "iPhone 14",
  "description": "Smartphone Apple de última geração",
  "imgUrl": "http://example.com/iphone.png",
  "price": 7000.00,
  "categories": [1, 2]
}
