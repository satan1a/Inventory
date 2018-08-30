Controller说明
---
- 总：控制层作用于模型层和视图层，在本项目中，控制层的控制器通过对模型层的操作进而影响数据库。

- 下面简单介绍control中各个控制器的作用：
    + CriteriaQueryLayoutController     =》 条件查询
    + EditDialogController              =》 编辑记录
    + LoginDialogController             =》 登入验证
    + MainLayoutController              =》 加载主页面（fxml）
    + ManageLayoutController            =》 记录管理
    + Modern                            =》 数据库中代表表的常量
    + ViewModern                        =》 页面中的 条件查询、记录管理 选项

- 另：各个控制器的详细作用和具体方法在对应类中已写出注释。