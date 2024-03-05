package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Wallet {

    String owner;
    List<String> cards;
    HashMap<Integer, Integer> moneys;
    HashMap<Integer, Integer> coins;

    int[] coinTypes = {100, 500, 1000};
    int[] moneyTypes = {1000, 2000, 5000, 10000, 20000, 50000, 100000};

    public Wallet(String owner) {
        this.owner = owner;

        this.coins = new HashMap<Integer, Integer>();
        for (int i=0; i<coinTypes.length; i++) {
            this.coins.put(coinTypes[i], 0 );
        }

        this.moneys = new HashMap<Integer, Integer>();
        for (int i=0; i<moneyTypes.length; i++) {
            this.moneys.put(moneyTypes[i], 0);
        }

        this.cards = new ArrayList<String>();
    }

    public String getOwner() { return owner; }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getCards() {
        return cards;
    }

    public void addCard(String card) {
        this.cards.add(card);
    }

    public void takeCard(String card) {
        this.cards.remove(card);
    }

    public HashMap<Integer, Integer> getMoneys() {
        return moneys;
    }

    public void addMoney(Integer moneyType) {
        boolean isMoneyValid = false;

        for (int i=0; i<moneyTypes.length; i++) {
            if (moneyType == moneyTypes[i]) {
                isMoneyValid = true;
            }
        }

        if (isMoneyValid) {
            int initialValue = this.moneys.get(moneyType);
            this.moneys.put(moneyType, initialValue+1);
        }
    }

    public void addCoin(Integer coinType) {
        boolean isCoinValid = false;

        for (int i=0; i<coinTypes.length; i++) {
            if (coinType == coinTypes[i]) {
                isCoinValid= true;
            }
        }
        if (isCoinValid) {
            int inintialValue = this.coins.get(coinType);
            this.coins.put(coinType, inintialValue+1);
        }
    }

    public void takeCoins(Integer coinType) {
        int initialValue = this.coins.get(coinType);
        this.coins.put(coinType, initialValue - 1);
    }

    public void takeMoneys(Integer moneyType) {
        int initialValue = this.moneys.get(moneyType);
        this.moneys.put(moneyType, initialValue - 1);
    }

    public HashMap<Integer, Integer> getCoins() {
        return coins;
    }

    public int getMoneyAvailable() {
        int total = this.calculateCoins() + this.calculateMoneys();
        return total;
    }

    public int calculateCoins() {
        int total = 0;
        for (Integer key : this.coins.keySet()) {
            total += key * this.coins.get(key);
        }
        return total;
    }

    public int calculateMoneys() {
        int total = 0;
        for (Integer key : this.moneys.keySet()) {
            total += key * this.moneys.get(key);
        }
        return total;
    }
}