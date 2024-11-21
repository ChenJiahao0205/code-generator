package pers.chenjiahao.codegenerator.service;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import pers.chenjiahao.codegenerator.properties.GeneratorProperties;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

/**
 * Generator Service
 */
@Slf4j
@Service
public class GeneratorService {

    @Autowired
    private GeneratorProperties generatorProperties;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    public void execute() {
        log.info("===============start===================");
        var mpg = new AutoGenerator();

        var gc = new GlobalConfig();
        gc.setOutputDir(generatorProperties.getOutPutDir());
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setSwagger2(true);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setAuthor(generatorProperties.getAuthor());

        mpg.setGlobalConfig(gc);

        var dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(dataSourceProperties.getUsername());
        dsc.setPassword(dataSourceProperties.getPassword());
        dsc.setUrl(dataSourceProperties.getUrl());
        mpg.setDataSource(dsc);

        var strategy = new StrategyConfig();
        strategy.setTablePrefix(generatorProperties.getTablePrefix());
        strategy.setInclude(generatorProperties.getIncludeTable());
        strategy.setExclude(generatorProperties.getExcludeTable());
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        var pc = new PackageConfig();
        pc.setParent(generatorProperties.getPackageName());
        pc.setModuleName(generatorProperties.getModuleName());
        pc.setEntity("model");
        mpg.setPackageInfo(pc);

        mpg.execute();
        log.info("===============finish===================");
    }
}
