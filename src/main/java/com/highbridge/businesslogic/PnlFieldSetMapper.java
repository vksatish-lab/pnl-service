package com.highbridge.businesslogic;

import com.highbridge.domain.Pnl;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Component
public class PnlFieldSetMapper implements FieldSetMapper<Pnl> {

    @Override
    public Pnl mapFieldSet(FieldSet fieldSet) {
        final Pnl pnl = new Pnl();
        pnl.setTicker(fieldSet.readString("ticker"));
        pnl.setQuantity(fieldSet.readInt("quantity"));
        return pnl;
    }
}
