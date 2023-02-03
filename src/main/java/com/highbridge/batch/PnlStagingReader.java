package com.highbridge.batch;

import com.highbridge.domain.PnlLoadStagging;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component("pnlStagingReader")
@StepScope
public class PnlStagingReader {

    private static final String GET_STAGING_RECORD = "SELECT loadRequestId,ticker,quantity,pnl from PnlLoadStaging where loadRequestId = :requestId ";

    @Autowired
    private DataSource dataSource;

    public JdbcPagingItemReader<PnlLoadStagging> getPaginationReader() {
        final String requestId = "requestId"; // find a way to pass this parameter
        final JdbcPagingItemReader<PnlLoadStagging> reader = new JdbcPagingItemReader<>();
        final PnlStaggingRowMapper stagingRowMapper = new PnlStaggingRowMapper();
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);
        reader.setPageSize(100);
        reader.setRowMapper(stagingRowMapper);
        reader.setQueryProvider(createQuery());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("requestId", requestId);
        reader.setParameterValues(parameters);
        return reader;
    }

    private PostgresPagingQueryProvider createQuery() {
        final Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);
        final PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider();
        queryProvider.setSelectClause("*");
        queryProvider.setFromClause(getFromClause());
        queryProvider.setSortKeys(sortKeys);
        return queryProvider;
    }

    private String getFromClause() {
        return "( " + GET_STAGING_RECORD + ")" + " AS RESULT_TABLE ";
    }

    public class PnlStaggingRowMapper implements RowMapper<PnlLoadStagging> {

        @Override
        public PnlLoadStagging mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
            PnlLoadStagging record = new PnlLoadStagging();
            record.setPnl(rs.getInt("pnl"));
            record.setTicker(rs.getString("ticker"));
            // set all fields here
            return record;
        }
    }

}
