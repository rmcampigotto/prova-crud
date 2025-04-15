
# CRUD Start Project - Java + Spring Boot + JPA + H2 + DTO

Este repositório serve como base para provas e desafios que envolvem:
- Spring Boot
- Spring Data JPA
- Spring Web
- Banco de dados H2 (em memória)
- Padrão DTO (Data Transfer Object)

---

## 🧱 Estrutura sugerida

- `entity/` → Suas entidades JPA
- `dto/` → Objetos para entrada/saída de dados
- `repository/` → Interfaces que estendem JpaRepository
- `service/` → Regras de negócio
- `controller/` → Endpoints REST

---

## 💡 Exemplo de CRUD com Produto

### 🔹 Produto (Entity)

```java
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double preco;
}
```

### 🔹 ProdutoDTO

```java
public class ProdutoDTO {
    private String nome;
    private double preco;
}
```

### 🔹 ProdutoRepository

```java
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
```

### 🔹 ProdutoService

```java
@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(ProdutoDTO dto) {
        Produto p = new Produto();
        p.setNome(dto.getNome());
        p.setPreco(dto.getPreco());
        return repository.save(p);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
```

### 🔹 ProdutoController

```java
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody ProdutoDTO dto) {
        Produto salvo = service.salvar(dto);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
```

---

## ⚙️ application.properties (banco H2)

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

---

## ✅ Para rodar

```bash
./mvnw spring-boot:run
```

Abra: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

Boa prova! 🚀

---

## 🛠️ Passo a passo para construir seu CRUD do zero

Este guia funciona para qualquer entidade que o professor pedir. Basta substituir `Produto` pelo nome da entidade desejada.

### 1. Criar a classe de entidade (model)

```java
@Entity
public class NomeDaEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Adicione os campos conforme a entidade
    private String nome;
    private double valor;
}
```

### 2. Criar o DTO (Data Transfer Object)

```java
public class NomeDaEntidadeDTO {
    private String nome;
    private double valor;
}
```

### 3. Criar o Repository

```java
public interface NomeDaEntidadeRepository extends JpaRepository<NomeDaEntidade, Long> {}
```

### 4. Criar o Service

```java
@Service
public class NomeDaEntidadeService {

    @Autowired
    private NomeDaEntidadeRepository repository;

    public NomeDaEntidade salvar(NomeDaEntidadeDTO dto) {
        NomeDaEntidade entidade = new NomeDaEntidade();
        entidade.setNome(dto.getNome());
        entidade.setValor(dto.getValor());
        return repository.save(entidade);
    }

    public List<NomeDaEntidade> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
```

### 5. Criar o Controller

```java
@RestController
@RequestMapping("/entidades")
public class NomeDaEntidadeController {

    @Autowired
    private NomeDaEntidadeService service;

    @PostMapping
    public ResponseEntity<NomeDaEntidade> criar(@RequestBody NomeDaEntidadeDTO dto) {
        NomeDaEntidade salvo = service.salvar(dto);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<NomeDaEntidade> listar() {
        return service.listarTodos();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
```

### 6. Configurar o `application.properties`

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

---

✅ Agora é só testar seus endpoints com Postman, Insomnia ou pelo navegador se for GET.

Boa sorte na prova! 🍀
