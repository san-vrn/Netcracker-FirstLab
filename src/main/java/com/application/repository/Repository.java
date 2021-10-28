package com.application.repository;

import com.application.contracts.Contracts;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * This class is intended for storing Contracts
 */
public class Repository {

    private Contracts[] contracts;
    private int size;


    public Repository() {
        this.contracts = new Contracts[10];
        this.size = 0;
    }

    /**
     * This is a method for adding a contract to an array
     * @param contract stores a Contract type object
     */
    public void add(Contracts contract){
        if (size == contracts.length-1){
            increaseMassive();
        }
        contracts[size++] = contract;
    }

    /**
     * This method removes the contract from the array by id
     * @param id value for transmitting the contract id
     */
    public void remove(int id){
        int index = getIndexElement(id);
        if (index == -1) return;
        System.arraycopy(contracts, index + 1, contracts, index, size - index);
        size--;
        if((contracts.length - size) > 11){
            decreaseMassive();
        }
    }

    /**
     * This is a method for getting a Contract type object by id
     * @param id value for transmitting the contract id
     * @return an object of type Optional containing a Сontract
     */
    public Optional<Contracts> getContract(int id) {
        for (int i = 0; i < size; i++) {
            if (contracts[i].getID() == id) return Optional.of(contracts[i]);
        }
        return Optional.empty();
    }

    /**
     *This is a method for increasing array cells
     */
    private void increaseMassive(){
        Contracts[] tempArray =(Contracts[]) new Object[contracts.length + 10];
        System.arraycopy(contracts, 0, tempArray, 0, contracts.length);
        contracts = tempArray;
    }

    /**
     *this is a method for decreasing array cells
     */
    private void decreaseMassive(){
        Contracts[] tempArray =(Contracts[]) new Object[contracts.length - 10];
        System.arraycopy(contracts, 0, tempArray, 0, contracts.length - 10);
        contracts = tempArray;
    }

    /**
     *This is a method for getting the cell number that contains a contract with a given id
     * @param id the value of the desired contract
     * @return cell number in the array
     */
    private int getIndexElement(int id){
        for (int i = 0; i < size; i++) {
            if (contracts[i].getID() == id) return i;
        }
        return -1;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return size == that.size && Arrays.equals(contracts, that.contracts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(contracts);
        return result;
    }
}

