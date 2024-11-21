package pers.chenjiahao.codegenerator.service;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import pers.chenjiahao.codegenerator.properties.GeneratorProperties;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generator Service
 */
@Slf4j
@Service
public class GeneratorCustomerService {

    @Autowired
    private GeneratorProperties generatorProperties;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    private static final String resourcePath = "templatesCustomerRewritedVm/";

    public void execute() {
        log.info("===============start===================");
        // 代码生成器
        var mpg = new AutoGenerator();

        // 全局配置
        var gc = new GlobalConfig();
        gc.setOutputDir(generatorProperties.getOutPutDir());
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setSwagger2(true);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setAuthor(generatorProperties.getAuthor());
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        var dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(dataSourceProperties.getUsername());
        dsc.setPassword(dataSourceProperties.getPassword());
        dsc.setUrl(dataSourceProperties.getUrl());
        mpg.setDataSource(dsc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别

        templateConfig.setController(resourcePath + "controller.java");
        templateConfig.setService(resourcePath + "service.java");
        templateConfig.setServiceImpl(resourcePath + "serviceImpl.java");
        templateConfig.setMapper(resourcePath + "mapper.java");
        templateConfig.setXml(resourcePath + "mapper.xml");
        templateConfig.setEntity(resourcePath + "entity.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
//                map.put("serviceFieldName", "$entity.substring(0,1).toLowerCase()$entity.substring(1)Service");
                this.setMap(map);
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(resourcePath + "modelVo.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generatorProperties.getOutPutDir()
                        + generatorProperties.getFilePackageName()
                        + "/model/"
                        + "vo/"
                        + tableInfo.getEntityName() + "VO.java";
            }
        });
        focList.add(new FileOutConfig(resourcePath + "modelCreateReq.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generatorProperties.getOutPutDir()
                        + generatorProperties.getFilePackageName()
                        + "/model/"
                        + "req/"
                        + tableInfo.getEntityName() + "CreateReq.java";
            }
        });
        focList.add(new FileOutConfig(resourcePath + "modelUpdateReq.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generatorProperties.getOutPutDir()
                        + generatorProperties.getFilePackageName()
                        + "/model/"
                        + "req/"
                        + tableInfo.getEntityName() + "UpdateReq.java";
            }
        });
        focList.add(new FileOutConfig(resourcePath + "modelQueryReq.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generatorProperties.getOutPutDir()
                        + generatorProperties.getFilePackageName()
                        + "/model/"
                        + "req/"
                        + tableInfo.getEntityName() + "QueryReq.java";
            }
        });

        cfg.setFileOutConfigList(focList);

        mpg.setCfg(cfg);

        // 策略配置
        var strategy = new StrategyConfig();
        strategy.setTablePrefix(generatorProperties.getTablePrefix());
        strategy.setInclude(generatorProperties.getIncludeTable());
        strategy.setExclude(generatorProperties.getExcludeTable());
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        // 包配置
        var pc = new PackageConfig();
        pc.setParent(generatorProperties.getPackageName());
        pc.setModuleName(generatorProperties.getModuleName());
        pc.setController("controller");
        pc.setEntity("model.entity");
        mpg.setPackageInfo(pc);

//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        log.info("===============finish===================");
    }
}
