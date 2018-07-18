package com.example.HuiWang_SummerProject;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class VerificationSmartContract extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506106cd8061005e6000396000f300606060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806324318a2f14610067578063a775314a146100a0578063ab28982914610105578063c23697a81461021b575b600080fd5b341561007257600080fd5b61009e600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061026c565b005b34156100ab57600080fd5b6100eb60048080356000191690602001909190803560ff16906020019091908035600019169060200190919080356000191690602001909190505061030b565b604051808215151515815260200191505060405180910390f35b341561011057600080fd5b610219600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919080356000191690602001909190803560ff1690602001909190803560001916906020019091908035600019169060200190919050506103ce565b005b341561022657600080fd5b610252600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506105ce565b604051808215151515815260200191505060405180910390f35b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156102c757600080fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060008660405180826000191660001916815260200191505060405180910390209150600187878787604051600081526020016040526040518085600019166000191681526020018460ff1660ff1681526020018360001916600019168152602001826000191660001916815260200194505050505060206040516020810390808403906000865af115156103a157600080fd5b50506020604051035190506103b5816105ce565b156103c357600192506103c4565b5b5050949350505050565b60006103dc8585858561030b565b156105c457600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff16630460442f8989896040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845287818151815260200191508051906020019080838360005b8381101561049857808201518184015260208101905061047d565b50505050905090810190601f1680156104c55780820380516001836020036101000a031916815260200191505b50848103835286818151815260200191508051906020019080838360005b838110156104fe5780820151818401526020810190506104e3565b50505050905090810190601f16801561052b5780820380516001836020036101000a031916815260200191505b50848103825285818151815260200191508051906020019080838360005b83811015610564578082015181840152602081019050610549565b50505050905090810190601f1680156105915780820380516001836020036101000a031916815260200191505b509650505050505050600060405180830381600087803b15156105b357600080fd5b5af115156105c057600080fd5b5050505b5050505050505050565b600080732f392014df740a506616f6854a14bdaff923df1390508073ffffffffffffffffffffffffffffffffffffffff1663ae22c57d846040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561068257600080fd5b5af1151561068f57600080fd5b505050604051805190509150509190505600a165627a7a7230582043e0347c3588642f605806d2132329582fc40f79dfa7cb388c975c25f97b519a0029";

    protected VerificationSmartContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VerificationSmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> setVerifiedDataAddress(String anAddress) {
        final Function function = new Function(
                "setVerifiedDataAddress", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(anAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> decryptSig(byte[] _message, BigInteger _v, byte[] _r, byte[] _s) {
        final Function function = new Function(
                "decryptSig", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_message), 
                new org.web3j.abi.datatypes.generated.Uint8(_v), 
                new org.web3j.abi.datatypes.generated.Bytes32(_r), 
                new org.web3j.abi.datatypes.generated.Bytes32(_s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> verify(String fingerPrint, String producer, byte[] videoBytes, byte[] _message, BigInteger _v, byte[] _r, byte[] _s) {
        final Function function = new Function(
                "verify", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(fingerPrint), 
                new org.web3j.abi.datatypes.Utf8String(producer), 
                new org.web3j.abi.datatypes.DynamicBytes(videoBytes), 
                new org.web3j.abi.datatypes.generated.Bytes32(_message), 
                new org.web3j.abi.datatypes.generated.Uint8(_v), 
                new org.web3j.abi.datatypes.generated.Bytes32(_r), 
                new org.web3j.abi.datatypes.generated.Bytes32(_s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> check(String checkAddress) {
        final Function function = new Function(
                "check", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(checkAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<VerificationSmartContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VerificationSmartContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<VerificationSmartContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VerificationSmartContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static VerificationSmartContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VerificationSmartContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static VerificationSmartContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VerificationSmartContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
