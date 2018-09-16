package com.test.votting.vottingtest.SolCode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
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
 * <p>Generated with web3j version 3.5.0.
 */
public class Judgment extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610667806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063dcf0603c14610051578063e24c09eb146101ac575b600080fd5b34801561005d57600080fd5b506101aa600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061024d565b005b3480156101b857600080fd5b50610233600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061038e565b604051808215151515815260200191505060405180910390f35b60a0604051908101604052808673ffffffffffffffffffffffffffffffffffffffff168152602001858152602001848152602001838152602001828152506000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550602082015181600101908051906020019061032c929190610596565b506040820151816002019080519060200190610349929190610596565b506060820151816003019080519060200190610366929190610596565b506080820151816004019080519060200190610383929190610596565b509050505050505050565b6000816040516020018082805190602001908083835b6020831015156103c957805182526020820191506020810190506020830392506103a4565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b602083101515610432578051825260208201915060208101905060208303925061040d565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160405160200180828054600181600116156101000203166002900480156105025780601f106104e0576101008083540402835291820191610502565b820191906000526020600020905b8154815290600101906020018083116104ee575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310151561054b5780518252602082019150602081019050602083039250610526565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916141561058b5760019050610590565b600090505b92915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106105d757805160ff1916838001178555610605565b82800160010185558215610605579182015b828111156106045782518255916020019190600101906105e9565b5b5090506106129190610616565b5090565b61063891905b8082111561063457600081600090555060010161061c565b5090565b905600a165627a7a7230582020304700541bfd0442dbe4be05f1e2c6f6bef7f37b8c2b8867865de369e9c6f20029";

    public static final String FUNC_ADDJUDGMENTINFORMATION = "addJudgmentInformation";

    public static final String FUNC_CHECKIDANDPASSWORD = "checkIdAndPassword";

    protected Judgment(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Judgment(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> addJudgmentInformation(String _address, String judgmentInformationId, String name, String birthOfDate, String password) {
        final Function function = new Function(
                FUNC_ADDJUDGMENTINFORMATION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address),
                        new org.web3j.abi.datatypes.Utf8String(judgmentInformationId),
                        new org.web3j.abi.datatypes.Utf8String(name),
                        new org.web3j.abi.datatypes.Utf8String(birthOfDate),
                        new org.web3j.abi.datatypes.Utf8String(password)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> checkIdAndPassword(String _address, String password) {
        final Function function = new Function(FUNC_CHECKIDANDPASSWORD,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address),
                        new org.web3j.abi.datatypes.Utf8String(password)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public static RemoteCall<Judgment> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Judgment.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Judgment> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Judgment.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Judgment load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Judgment(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Judgment load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Judgment(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
