package com.test.votting.vottingtest.SolCode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
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
public class Candidates extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50611007806100206000396000f3006080604052600436106100e55763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630d3c05b181146100ea5780631e0efcf3146101805780633e2b1ae7146101a15780634bd231db146101d457806361fe3163146101f55780636efdce6d1461026a5780638cd32d5a1461028b57806394e4bf6c146102a35780639a55605e146102c45780639e611f11146104255780639ff00ac114610449578063b41435a31461045e578063b567c63c1461047f578063ba13af67146105a0578063c27eb745146101a1578063e24c09eb146105c1575b600080fd5b3480156100f657600080fd5b5061010b600160a060020a036004351661063c565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561014557818101518382015260200161012d565b50505050905090810190601f1680156101725780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561018c57600080fd5b5061010b600160a060020a03600435166106ea565b3480156101ad57600080fd5b506101c2600160a060020a036004351661075e565b60408051918252519081900360200190f35b3480156101e057600080fd5b5061010b600160a060020a036004351661077c565b34801561020157600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261024e9436949293602493928401919081908401838280828437509497506107f39650505050505050565b60408051600160a060020a039092168252519081900360200190f35b34801561027657600080fd5b5061010b600160a060020a0360043516610864565b34801561029757600080fd5b5061024e6004356108db565b3480156102af57600080fd5b5061010b600160a060020a0360043516610907565b3480156102d057600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610423958335600160a060020a031695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375094975061097c9650505050505050565b005b34801561043157600080fd5b50610423600160a060020a0360043516602435610a7a565b34801561045557600080fd5b506101c2610ad2565b34801561046a57600080fd5b5061010b600160a060020a0360043516610ad9565b34801561048b57600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610423958335600160a060020a031695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610b539650505050505050565b3480156105ac57600080fd5b5061010b600160a060020a0360043516610cf3565b3480156105cd57600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610628958335600160a060020a0316953695604494919390910191908190840183828082843750949750610d689650505050505050565b604080519115158252519081900360200190f35b600160a060020a03811660009081526003602090815260409182902060040180548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156106de5780601f106106b3576101008083540402835291602001916106de565b820191906000526020600020905b8154815290600101906020018083116106c157829003601f168201915b50505050509050919050565b600160a060020a0381166000908152600260208181526040928390208201805484516001821615610100026000190190911693909304601f810183900483028401830190945283835260609390918301828280156106de5780601f106106b3576101008083540402835291602001916106de565b600160a060020a031660009081526004602052604090206001015490565b600160a060020a03811660009081526003602090815260409182902060050180548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156106de5780601f106106b3576101008083540402835291602001916106de565b60006001826040518082805190602001908083835b602083106108275780518252601f199092019160209182019101610808565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054600160a060020a0316949350505050565b600160a060020a03811660009081526003602081815260409283902090910180548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156106de5780601f106106b3576101008083540402835291602001916106de565b60006005828154811015156108ec57fe5b600091825260209091200154600160a060020a031692915050565b600160a060020a038116600090815260026020818152604092839020600301805484516001821615610100026000190190911693909304601f810183900483028401830190945283835260609390918301828280156106de5780601f106106b3576101008083540402835291602001916106de565b6040805160c081018252600160a060020a0388811680835260208084018a81528486018a9052606085018990526080850188905260a08501879052600092835260038252949091208351815473ffffffffffffffffffffffffffffffffffffffff1916931692909217825592518051929391926109ff9260018501920190610f43565b5060408201518051610a1b916002840191602090910190610f43565b5060608201518051610a37916003840191602090910190610f43565b5060808201518051610a53916004840191602090910190610f43565b5060a08201518051610a6f916005840191602090910190610f43565b505050505050505050565b604080518082018252600160a060020a039384168082526020808301948552600091825260049052919091209051815473ffffffffffffffffffffffffffffffffffffffff1916931692909217825551600190910155565b6005545b90565b600160a060020a0381166000908152600360209081526040918290206002908101805484516001821615610100026000190190911692909204601f810184900484028301840190945283825260609391929091908301828280156106de5780601f106106b3576101008083540402835291602001916106de565b6005805460018181019092557f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db0018054600160a060020a0380891673ffffffffffffffffffffffffffffffffffffffff1992831681179093556040805160a08101825284815260208181018b81528284018b9052606083018a905260808301899052600096875260028252929095208151815494169390941692909217835551805191949293610c0893850192910190610f43565b5060408201518051610c24916002840191602090910190610f43565b5060608201518051610c40916003840191602090910190610f43565b5060808201518051610c5c916004840191602090910190610f43565b50905050846001856040518082805190602001908083835b60208310610c935780518252601f199092019160209182019101610c74565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03949094169390931790925550505050505050565b600160a060020a038116600090815260026020818152604092839020600401805484516001821615610100026000190190911693909304601f810183900483028401830190945283835260609390918301828280156106de5780601f106106b3576101008083540402835291602001916106de565b6000816040516020018082805190602001908083835b60208310610d9d5780518252601f199092019160209182019101610d7e565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b60208310610e005780518252601f199092019160209182019101610de1565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166002600085600160a060020a0316600160a060020a031681526020019081526020016000206004016040516020018082805460018160011615610100020316600290048015610eb75780601f10610e95576101008083540402835291820191610eb7565b820191906000526020600020905b815481529060010190602001808311610ea3575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310610efa5780518252601f199092019160209182019101610edb565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415610f3957506001610f3d565b5060005b92915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f8457805160ff1916838001178555610fb1565b82800160010185558215610fb1579182015b82811115610fb1578251825591602001919060010190610f96565b50610fbd929150610fc1565b5090565b610ad691905b80821115610fbd5760008155600101610fc75600a165627a7a72305820cfbeaedee1de4a4f71c16837a78b564e0723cd5aa8016281de85d01917e1a7b00029";

    public static final String FUNC_GETCANDIDATEPHONENUMBER = "getCandidatePhonenumber";

    public static final String FUNC_GETCANDIDATENAME = "getCandidateName";

    public static final String FUNC_GETCANDIDATENUMBEROFVOTES = "getCandidateNumberOfVotes";

    public static final String FUNC_GETCANDIDATECAMPAIGN = "getCandidateCampaign";

    public static final String FUNC_GETCANDIDATEADDRESSBYNATIONALID = "getCandidateAddressByNationalId";

    public static final String FUNC_GETCANDIDATEYEAR = "getCandidateYear";

    public static final String FUNC_GETNATIONALID = "getNationalID";

    public static final String FUNC_GETCANDIDATEBIRTHOFDATE = "getCandidatebirthOfDate";

    public static final String FUNC_ADDCANDIDATEDETAILS = "addCandidateDetails";

    public static final String FUNC_ADDCANDIDATETRACKING = "addCandidateTracking";

    public static final String FUNC_GETNATIONALIDARRAYLENGTH = "getNationalIDArrayLength";

    public static final String FUNC_GETCANDIDATECITY = "getCandidateCity";

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_GETCANDIDATEPASSWORD = "getCandidatePassword";

    public static final String FUNC_GETCANDIDATEVOTESNUMBER = "getCandidateVotesNumber";

    public static final String FUNC_CHECKIDANDPASSWORD = "checkIdAndPassword";

    protected Candidates(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Candidates(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> getCandidatePhonenumber(String _address) {
        final Function function = new Function(FUNC_GETCANDIDATEPHONENUMBER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getCandidateName(String _address) {
        final Function function = new Function(FUNC_GETCANDIDATENAME,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getCandidateNumberOfVotes(String _address) {
        final Function function = new Function(FUNC_GETCANDIDATENUMBEROFVOTES,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getCandidateCampaign(String _address) {
        final Function function = new Function(FUNC_GETCANDIDATECAMPAIGN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getCandidateAddressByNationalId(String nationalId) {
        final Function function = new Function(FUNC_GETCANDIDATEADDRESSBYNATIONALID,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(nationalId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getCandidateYear(String _address) {
        final Function function = new Function(FUNC_GETCANDIDATEYEAR,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getNationalID(BigInteger index) {
        final Function function = new Function(FUNC_GETNATIONALID,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getCandidatebirthOfDate(String _address) {
        final Function function = new Function(FUNC_GETCANDIDATEBIRTHOFDATE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addCandidateDetails(String _address, String candidateIdNumber, String city, String year, String phoneNumber, String campaign) {
        final Function function = new Function(
                FUNC_ADDCANDIDATEDETAILS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address),
                        new org.web3j.abi.datatypes.Utf8String(candidateIdNumber),
                        new org.web3j.abi.datatypes.Utf8String(city),
                        new org.web3j.abi.datatypes.Utf8String(year),
                        new org.web3j.abi.datatypes.Utf8String(phoneNumber),
                        new org.web3j.abi.datatypes.Utf8String(campaign)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addCandidateTracking(String _address, BigInteger numberOfVotes) {
        final Function function = new Function(
                FUNC_ADDCANDIDATETRACKING,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address),
                        new org.web3j.abi.datatypes.generated.Uint256(numberOfVotes)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getNationalIDArrayLength() {
        final Function function = new Function(FUNC_GETNATIONALIDARRAYLENGTH,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getCandidateCity(String _address) {
        final Function function = new Function(FUNC_GETCANDIDATECITY,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addCandidate(String _address, String candidateIdNumber, String name, String birthOfDate, String password) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address),
                        new org.web3j.abi.datatypes.Utf8String(candidateIdNumber),
                        new org.web3j.abi.datatypes.Utf8String(name),
                        new org.web3j.abi.datatypes.Utf8String(birthOfDate),
                        new org.web3j.abi.datatypes.Utf8String(password)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getCandidatePassword(String _address) {
        final Function function = new Function(FUNC_GETCANDIDATEPASSWORD,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getCandidateVotesNumber(String _address) {
        final Function function = new Function(FUNC_GETCANDIDATEVOTESNUMBER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> checkIdAndPassword(String _address, String password) {
        final Function function = new Function(FUNC_CHECKIDANDPASSWORD,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address),
                        new org.web3j.abi.datatypes.Utf8String(password)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public static RemoteCall<Candidates> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Candidates.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Candidates> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Candidates.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Candidates load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Candidates(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Candidates load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Candidates(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
