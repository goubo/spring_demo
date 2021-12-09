package generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.function.Consumer;

/**
 * @author bo
 */
public class MyBatisPlusGenerator {
  
  private final static String url = "jdbc:mysql://172.16.204.147:3306/demodb?useSSL=false&allowPublicKeyRetrieval=true";
  private final static String userName = "root";
  private final static String password = "H%Rt?)QjM^j?M8";
  private final static String parent = "com.bobo.demo";
  
  private final static boolean fileOverride = true;
  
  private final static String productPath = "/Users/bo/my/git/spring_demo";
  private final static String moduleName = "user";
  private final static String author = "bobo";
  
  
  /**
   * 全局配置
   */
  private final static Consumer<GlobalConfig.Builder> globalConfig = builder -> {
    // 设置作者
    builder.author(author)
      // 开启 swagger 模式
      .enableSwagger()
      // 禁止打开输出目录
      .disableOpenDir()
      .dateType(DateType.TIME_PACK)
      // 指定输出目录
      .outputDir(productPath + "/" + moduleName + "/src/main/java");
    // 覆盖已生成文件
    if (fileOverride) {
      builder.fileOverride();
    }
    
  };
  
  private final static Consumer<PackageConfig.Builder> packageConfig = builder ->
    builder
      .parent(parent)
      .moduleName(moduleName)
      .build();
  
  
  private final static Consumer<StrategyConfig.Builder> strategyConfig = builder ->
    builder
      .entityBuilder()
      .superClass("com.bobo.demo.common.base.BaseDomain")
      .enableLombok()
      .enableColumnConstant()
      .enableChainModel()
      .enableRemoveIsPrefix()
      .enableActiveRecord()
      .versionColumnName("version")
      .versionPropertyName("version")
      .logicDeleteColumnName("is_deleted")
      .logicDeletePropertyName("deleteFlag")
      .addSuperEntityColumns("id", "is_deleted", "create_time", "update_time", "create_id", "update_id")
      .addTableFills(new Column("create_time", FieldFill.INSERT))
      .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
      .idType(IdType.ASSIGN_ID)
      .controllerBuilder()
      .superClass("com.bobo.demo.common.base.BaseController")
      .enableHyphenStyle()
      .enableRestStyle()
      .serviceBuilder()
      .superServiceClass("com.bobo.demo.common.base.IBaseService")
      .superServiceImplClass("com.bobo.demo.common.base.BaseServiceImpl")
      .mapperBuilder()
      .enableMapperAnnotation()
      .enableBaseResultMap()
      .enableBaseColumnList();
  
  
  public static void main(String[] args) {
    FastAutoGenerator.create(url, userName, password)
      .globalConfig(globalConfig)
      .packageConfig(packageConfig)
      .templateEngine(new FreemarkerTemplateEngine())
      .strategyConfig(strategyConfig)
      .execute();
    
  }
  
}
