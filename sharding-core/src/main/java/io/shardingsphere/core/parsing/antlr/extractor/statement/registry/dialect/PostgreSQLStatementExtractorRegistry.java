/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.extractor.statement.registry.dialect;

import io.shardingsphere.core.parsing.antlr.extractor.statement.SQLStatementExtractor;
import io.shardingsphere.core.parsing.antlr.extractor.statement.SQLStatementType;
import io.shardingsphere.core.parsing.antlr.extractor.statement.engine.dal.dialect.postgresql.PostgreSQLResetParamExtractor;
import io.shardingsphere.core.parsing.antlr.extractor.statement.engine.dal.dialect.postgresql.PostgreSQLSetParamExtractor;
import io.shardingsphere.core.parsing.antlr.extractor.statement.engine.dal.dialect.postgresql.PostgreSQLShowExtractor;
import io.shardingsphere.core.parsing.antlr.extractor.statement.engine.ddl.CreateIndexExtractor;
import io.shardingsphere.core.parsing.antlr.extractor.statement.engine.ddl.CreateTableExtractor;
import io.shardingsphere.core.parsing.antlr.extractor.statement.engine.ddl.dialect.postgresql.*;
import io.shardingsphere.core.parsing.antlr.extractor.statement.engine.tcl.TCLStatementExtractor;
import io.shardingsphere.core.parsing.antlr.extractor.statement.registry.SQLStatementExtractorRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * SQL statement extractor registry for PostgreSQL.
 * 
 * @author duhongjun
 * @author zhangliang
 */
public final class PostgreSQLStatementExtractorRegistry implements SQLStatementExtractorRegistry {
    
    private static final Map<SQLStatementType, SQLStatementExtractor> EXTRACTORS = new HashMap<>();
    
    static {
        registerDDL();
        registerTCL();
        registerDAL();
    }
    
    private static void registerDDL() {
        EXTRACTORS.put(SQLStatementType.CREATE_TABLE, new CreateTableExtractor());
        EXTRACTORS.put(SQLStatementType.ALTER_TABLE, new PostgreSQLAlterTableExtractor());
        EXTRACTORS.put(SQLStatementType.DROP_TABLE, new PostgreSQLDropTableExtractor());
        EXTRACTORS.put(SQLStatementType.TRUNCATE_TABLE, new PostgreSQLTruncateTableExtractor());
        EXTRACTORS.put(SQLStatementType.CREATE_INDEX, new CreateIndexExtractor());
        EXTRACTORS.put(SQLStatementType.ALTER_INDEX, new PostgreSQLAlterIndexExtractor());
        EXTRACTORS.put(SQLStatementType.DROP_INDEX, new PostgreSQLDropIndexExtractor());
    }
    
    private static void registerTCL() {
        EXTRACTORS.put(SQLStatementType.SET_TRANSACTION, new TCLStatementExtractor());
        EXTRACTORS.put(SQLStatementType.COMMIT, new TCLStatementExtractor());
        EXTRACTORS.put(SQLStatementType.ROLLBACK, new TCLStatementExtractor());
        EXTRACTORS.put(SQLStatementType.SAVEPOINT, new TCLStatementExtractor());
        EXTRACTORS.put(SQLStatementType.BEGIN_WORK, new TCLStatementExtractor());
    }
    
    private static void registerDAL() {
        EXTRACTORS.put(SQLStatementType.SHOW, new PostgreSQLShowExtractor());
        EXTRACTORS.put(SQLStatementType.SET_PARAM, new PostgreSQLSetParamExtractor());
        EXTRACTORS.put(SQLStatementType.RESET_PARAM, new PostgreSQLResetParamExtractor());
    }

    @Override
    public SQLStatementExtractor getSQLStatementExtractor(final SQLStatementType type) {
        return EXTRACTORS.get(type);
    }
}
