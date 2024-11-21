package pers.chenjiahao.codegenerator.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 代码生成配置文件类
 */
@Data
@Component
@Accessors(chain = true)
@ConfigurationProperties(prefix = GeneratorProperties.PREFIX)
public class GeneratorProperties {

    public static final String PREFIX = "generator.config";

    /**
     * 注释作者
     */
    private String author;

    /**
     * 代码生成路径
     */
    private String outPutDir;

    /**
     * 表前缀，如hm_
     */
    private String[] tablePrefix;

    /**
     * 需要生成代码的表名，为空则表示全部生成
     */
    private String[] includeTable;

    /**
     * 不需要生成代码的表名
     */
    private String[] excludeTable;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 文件包名（/分割）
     */
    private String filePackageName;

    /**
     * 模块名
     */
    private String moduleName;
}
