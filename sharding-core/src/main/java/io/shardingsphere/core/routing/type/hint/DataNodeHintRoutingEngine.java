package io.shardingsphere.core.routing.type.hint;

import io.shardingsphere.core.parsing.parser.sql.SQLStatement;
import io.shardingsphere.core.routing.type.RoutingEngine;
import io.shardingsphere.core.routing.type.RoutingResult;
import io.shardingsphere.core.routing.type.RoutingTable;
import io.shardingsphere.core.routing.type.TableUnit;
import io.shardingsphere.core.rule.ShardingRule;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DataNodeHintRoutingEngine implements RoutingEngine {
    private final ShardingRule shardingRule;

    private final String logicTableName;

    private final SQLStatement sqlStatement;
    @Override
    public RoutingResult route() {
        RoutingResult result = new RoutingResult();

        String routedDataSourceName = DataNodeHintManager.getDataNodeSelector().selectDataSourceName();
        String routedTableName = DataNodeHintManager.getDataNodeSelector().selectTableName(logicTableName);
        TableUnit tableUnit = new TableUnit(routedDataSourceName);
        tableUnit.getRoutingTables().add(new RoutingTable(logicTableName, routedTableName));
        result.getTableUnits().getTableUnits().add(tableUnit);

        return result;
    }
}
