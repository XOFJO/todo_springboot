CREATE TABLE todo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    detail VARCHAR(500),
    status BOOLEAN DEFAULT FALSE
);

INSERT INTO todo (detail, status) VALUES
('完成项目文档编写', FALSE),
('学习 Spring Boot 框架', FALSE),
('设计数据库表结构', TRUE),
('实现用户认证功能', FALSE),
('编写单元测试用例', FALSE),
('优化数据库查询性能', FALSE),
('部署应用到生产环境', FALSE),
('修复前端页面布局问题', TRUE),
('集成第三方支付接口', FALSE),
('实现邮件通知功能', FALSE),
('优化代码性能和架构', FALSE),
('编写API接口文档', TRUE),
('实现数据备份策略', FALSE),
('配置CI/CD流水线', FALSE),
('进行安全性测试', FALSE),
('更新项目依赖版本', FALSE),
('实现缓存机制', FALSE),
('设计用户界面原型', TRUE),
('进行代码审查', FALSE),
('完成最终发布', FALSE);