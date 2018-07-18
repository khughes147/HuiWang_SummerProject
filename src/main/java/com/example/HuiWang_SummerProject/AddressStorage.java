package com.example.HuiWang_SummerProject;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.1.
 */
public class AddressStorage extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b61080a8061001e6000396000f30060606040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806329092d0e14610072578063447d8930146100ab5780634ef7bd0f1461017a578063a87d942c146101f6578063ae22c57d1461021f575b600080fd5b341561007d57600080fd5b6100a9600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610270565b005b34156100b657600080fd5b6100cc6004808035906020019091905050610399565b60405180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b8381101561013e578082015181840152602081019050610123565b50505050905090810190601f16801561016b5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b341561018557600080fd5b6101f4600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506104a8565b005b341561020157600080fd5b61020961059f565b6040518082815260200191505060405180910390f35b341561022a57600080fd5b610256600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506105ab565b604051808215151515815260200191505060405180910390f35b60007383e08a5e77901126a16721590a8e1379fb7b1b6b3373ffffffffffffffffffffffffffffffffffffffff161415156102aa57600080fd5b600090505b600080549050811015610395578173ffffffffffffffffffffffffffffffffffffffff166000828154811015156102e257fe5b906000526020600020906002020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156103885760008181548110151561033f57fe5b90600052602060002090600202016000808201600061035e9190610655565b6001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905550505b80806001019150506102af565b5050565b6103a161069d565b600080838154811015156103b157fe5b90600052602060002090600202016000016000848154811015156103d157fe5b906000526020600020906002020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104985780601f1061046d57610100808354040283529160200191610498565b820191906000526020600020905b81548152906001019060200180831161047b57829003601f168201915b5050505050915091509150915091565b7383e08a5e77901126a16721590a8e1379fb7b1b6b3373ffffffffffffffffffffffffffffffffffffffff161415156104e057600080fd5b600080548060010182816104f491906106b1565b9160005260206000209060020201600060408051908101604052808681526020018573ffffffffffffffffffffffffffffffffffffffff16815250909190915060008201518160000190805190602001906105509291906106e3565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050505050565b60008080549050905090565b600080600090505b60008054905081101561064a576000818154811015156105cf57fe5b906000526020600020906002020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff16141561063d576001915061064f565b80806001019150506105b3565b600091505b50919050565b50805460018160011615610100020316600290046000825580601f1061067b575061069a565b601f0160209004906000526020600020908101906106999190610763565b5b50565b602060405190810160405280600081525090565b8154818355818115116106de576002028160020283600052602060002091820191016106dd9190610788565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061072457805160ff1916838001178555610752565b82800160010185558215610752579182015b82811115610751578251825591602001919060010190610736565b5b50905061075f9190610763565b5090565b61078591905b80821115610781576000816000905550600101610769565b5090565b90565b6107db91905b808211156107d757600080820160006107a79190610655565b6001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690555060020161078e565b5090565b905600a165627a7a72305820b4cb3b9ca359b0589e9716231c05d151a29b4ff53c0c627af226c22fce16f5a30029";

    protected AddressStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AddressStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> remove(String anAddress) {
        final Function function = new Function(
                "remove", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(anAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<String, String>> outputAddress(BigInteger count) {
        final Function function = new Function("outputAddress", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(count)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple2<String, String>>(
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> addProducer(String aProducer, String anAddress) {
        final Function function = new Function(
                "addProducer", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(aProducer), 
                new org.web3j.abi.datatypes.Address(anAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getCount() {
        final Function function = new Function("getCount", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> getAddress(String anAddress) {
        final Function function = new Function("getAddress", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(anAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public static RemoteCall<AddressStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AddressStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<AddressStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AddressStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static AddressStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AddressStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static AddressStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AddressStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
