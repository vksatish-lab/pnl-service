package com.highbridge.pnlservice;

import com.highbridge.domain.Pnl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Rest Controller to fetch most recent PNL
 */
@RestController
public class PnlService {

    /**
     * Get all PNL from the system
     * @return List<Pnl>
     */
    @GetMapping("/pnl/all")
    public List<Pnl> getAllPnl() {
        Pnl pnl = new Pnl();
        pnl.setTicker("AAPL");
        pnl.setQuantity(1000);
        pnl.setPnl(900000.00);
        return Arrays.asList(pnl);
    }

    /**
     * Get PNL a particular ticker.
     * @return List<Pnl>
     */
    @GetMapping("/pnl")
    public Pnl getPnl(@RequestParam(name = "ticker") String ticker) {
        Pnl pnl = new Pnl();
        pnl.setTicker(ticker);
        pnl.setQuantity(1000);
        pnl.setPnl(900000.00);
        return pnl;
    }
}
