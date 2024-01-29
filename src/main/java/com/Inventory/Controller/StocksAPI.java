package com.Inventory.Controller;

import com.Inventory.Model.Stocks;
import com.Inventory.Repo.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StocksAPI {

    @Autowired
    private StocksRepository StockRepo;

    @GetMapping()
    public ResponseEntity<List<Stocks>> getStocks(){
    try{
    List<Stocks> stocksList = new ArrayList<>();
    StockRepo.findAll().forEach(stocksList::add);
    if(stocksList.isEmpty())
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    return new ResponseEntity<>(stocksList,HttpStatus.OK);
    }
    catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    @PostMapping("/addStocks")
    public ResponseEntity<Stocks> addStock(@RequestBody Stocks stocks){
        Stocks stockObj=StockRepo.save(stocks);
        return new ResponseEntity<>(stockObj,HttpStatus.OK);
    }
    @PostMapping("/updateStocks/{id}")
    public ResponseEntity<Stocks> updateStockById(@PathVariable Long id,@RequestBody Stocks newStocks){
        Optional<Stocks> GetStockbyId = StockRepo.findById(id);
        if(GetStockbyId.isPresent()){
            Stocks stocks = GetStockbyId.get();
            stocks.setStocksAvailable(newStocks.getStocksAvailable()+100);
            Stocks save = StockRepo.save(stocks);
            return new ResponseEntity<>(save,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteStock/{id}")
    public ResponseEntity<Stocks> deleteStockById(@PathVariable Long id){
        StockRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
