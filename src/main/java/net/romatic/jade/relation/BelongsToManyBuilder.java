package net.romatic.jade.relation;

import java.lang.reflect.Field;
import java.util.List;

import net.romatic.com.collection.Models;
import net.romatic.jade.Builder;
import net.romatic.jade.Model;
import net.romatic.jade.annotation.BelongsToMany;
import net.romatic.utils.WordUtils;

/**
 * @author huiren
 */
public class BelongsToManyBuilder extends RelationBuilder {
    protected String pivotTable;

    @Override
    public void initBy(Field field) {
        // 获取注解
        BelongsToMany annotation = field.getAnnotation(BelongsToMany.class);
        // 获取关联的model
        Model related = RelationUtils.getRelated(field);
        // 创建builder
        Builder builder = related.newJadeQuery();
        // 当前model的主键
        String localKey = annotation.localKey();
        // 关联model的主键
        String relatedKey = annotation.relatedKey();
        // 中间表的表名
        pivotTable = annotation.table();
        // 如果关联model的主键是空字符，设置为类名_id
        if ("".equals(relatedKey)) {
            relatedKey = WordUtils.snake(field.getDeclaringClass().getSimpleName()) + "_id";
        }
        if ("".equals(pivotTable)) {
            pivotTable = builder.getModel().getTable() + "_" + related.getTable();
        }
        init(field.getName(),builder,builder.getModel(),localKey,relatedKey);
    }

    @Override
    public void withEagerConstraints() {
    }

    @Override
    public void withEagerConstraints(List<? extends Model> models) {

    }

    @Override
    public Models match(Models<? extends Model> models, Models relateds, String relationName) {
        return null;
    }
}
