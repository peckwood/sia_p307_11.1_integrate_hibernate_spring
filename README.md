Pitfalls:

- Need to make sure you are importing Hibernat4(not Hibernate3) packages if using Hibernate4.
- Need to add @Transactional to classes that use `session` or their caller classes.
- Need to add @Entity to domain/model/entity classes or hibernate will not recognize(and @Id, as well)
- Need to tell hibernate where the @Entity class is with `setPackagesToScan`
- Error: `No validator could be found for type: java.lang.Integer` @Range(min=5, max=99) is for numerics @Length is for String, @Size is for List
- Hibernate validator needs: `javax.el-api`, `hibernate-validator`, and `javax.validation.validation-api`