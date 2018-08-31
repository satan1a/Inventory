DAO 介绍
--
- DAO 即 Data Access Object，数据访问接口。作用：与数据库打交道，夹在业务逻辑与数据库资源之间。

- DAO模式是两个模式的组合，即Data Accessor (数据访问者)模式和 Active Domain Object(领域对象)模式。Data Accessor 模式实现了数据访问（与数据库直接接触）和业务逻辑（Controller）的分离；Active Domain Object 模式实现了业务数据的对象化封装（与Model层）。

- 实现　DAO　模式需要：　　
   + 一个 DAO 接口;  
   + 一个实现 DAO 接口的具体类;  
   + 数据传递对象(DTO): 有些时候叫做值对象(VO)或领域模型（domain）
- 下面简单介绍下dao包中各个文件的作用：  
    + GoodsDAO          =》 货物的增删改查
    + GoodsTypeDAO      =》 获取货物种类
    + RetrievalDAO      =》 对记录的增删改
    + StorageDAO        =》 库存信息的操作
    + SupplierDAO       =》 仓库信息的操作
    + UserDAO           =》 对用户的操作
- 详细的介绍见文件中的注释