package io.shardingsphere.core.routing.type.hint;

public interface DataNodeSelector {
    String selectDataSourceName();
    String selectTableName(String logicalTableName);
}
