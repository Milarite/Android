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
    private static final String BINARY = "608060405234801561001057600080fd5b506105a9806100206000396000f30060806040526004361061004b5763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663dcf0603c8114610050578063e24c09eb14610180575b600080fd5b34801561005c57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261017e95833573ffffffffffffffffffffffffffffffffffffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506102089650505050505050565b005b34801561018c57600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526101f495833573ffffffffffffffffffffffffffffffffffffffff169536956044949193909101919081908401838280828437509497506102ee9650505050505050565b604080519115158252519081900360200190f35b6040805160a08101825273ffffffffffffffffffffffffffffffffffffffff878116808352602080840189815284860189905260608501889052608085018790526000928352828252949091208351815473ffffffffffffffffffffffffffffffffffffffff19169316929092178255925180519293919261029092600185019201906104e2565b50604082015180516102ac9160028401916020909101906104e2565b50606082015180516102c89160038401916020909101906104e2565b50608082015180516102e49160048401916020909101906104e2565b5050505050505050565b6000816040516020018082805190602001908083835b602083106103235780518252601f199092019160209182019101610304565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b602083106103865780518252601f199092019160209182019101610367565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160405160200180828054600181600116156101000203166002900480156104565780601f10610434576101008083540402835291820191610456565b820191906000526020600020905b815481529060010190602001808311610442575b50509150506040516020818303038152906040526040518082805190602001908083835b602083106104995780518252601f19909201916020918201910161047a565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614156104d8575060016104dc565b5060005b92915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061052357805160ff1916838001178555610550565b82800160010185558215610550579182015b82811115610550578251825591602001919060010190610535565b5061055c929150610560565b5090565b61057a91905b8082111561055c5760008155600101610566565b905600a165627a7a72305820cd4a27987614cef493b4e3a7d3d5e7795ca55aae1c1c00504ced4d35d0ee98730029";

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
