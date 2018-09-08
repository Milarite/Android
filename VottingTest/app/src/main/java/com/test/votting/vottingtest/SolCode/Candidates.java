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
    private static final String BINARY = "608060405234801561001057600080fd5b50610e9a806100206000396000f3006080604052600436106100da5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630d3c05b181146100df5780631e0efcf3146101755780633e2b1ae7146101965780634bd231db146101c95780636efdce6d146101ea5780638cd32d5a1461020b57806394e4bf6c1461023f5780639a55605e146102605780639e611f11146103c15780639ff00ac1146103e5578063b41435a3146103fa578063b567c63c1461041b578063ba13af671461053c578063c27eb74514610196578063e24c09eb1461055d575b600080fd5b3480156100eb57600080fd5b50610100600160a060020a03600435166105d8565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561013a578181015183820152602001610122565b50505050905090810190601f1680156101675780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561018157600080fd5b50610100600160a060020a0360043516610684565b3480156101a257600080fd5b506101b7600160a060020a03600435166106f8565b60408051918252519081900360200190f35b3480156101d557600080fd5b50610100600160a060020a0360043516610716565b3480156101f657600080fd5b50610100600160a060020a036004351661078b565b34801561021757600080fd5b50610223600435610800565b60408051600160a060020a039092168252519081900360200190f35b34801561024b57600080fd5b50610100600160a060020a036004351661082c565b34801561026c57600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526103bf958335600160a060020a031695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506108a29650505050505050565b005b3480156103cd57600080fd5b506103bf600160a060020a03600435166024356109a0565b3480156103f157600080fd5b506101b76109f8565b34801561040657600080fd5b50610100600160a060020a03600435166109ff565b34801561042757600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526103bf958335600160a060020a031695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610a739650505050505050565b34801561054857600080fd5b50610100600160a060020a0360043516610b85565b34801561056957600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526105c4958335600160a060020a0316953695604494919390910191908190840183828082843750949750610bfb9650505050505050565b604080519115158252519081900360200190f35b600160a060020a038116600090815260026020818152604092839020600401805484516001821615610100026000190190911693909304601f810183900483028401830190945283835260609390918301828280156106785780601f1061064d57610100808354040283529160200191610678565b820191906000526020600020905b81548152906001019060200180831161065b57829003601f168201915b50505050509050919050565b600160a060020a0381166000908152600160208181526040928390206002908101805485519481161561010002600019011691909104601f810183900483028401830190945283835260609390918301828280156106785780601f1061064d57610100808354040283529160200191610678565b600160a060020a031660009081526003602052604090206001015490565b600160a060020a038116600090815260026020818152604092839020600501805484516001821615610100026000190190911693909304601f810183900483028401830190945283835260609390918301828280156106785780601f1061064d57610100808354040283529160200191610678565b600160a060020a038116600090815260026020818152604092839020600301805484516001821615610100026000190190911693909304601f810183900483028401830190945283835260609390918301828280156106785780601f1061064d57610100808354040283529160200191610678565b600060048281548110151561081157fe5b600091825260209091200154600160a060020a031692915050565b600160a060020a03811660009081526001602081815260409283902060030180548451600294821615610100026000190190911693909304601f810183900483028401830190945283835260609390918301828280156106785780601f1061064d57610100808354040283529160200191610678565b6040805160c081018252600160a060020a0388811680835260208084018a81528486018a9052606085018990526080850188905260a08501879052600092835260028252949091208351815473ffffffffffffffffffffffffffffffffffffffff1916931692909217825592518051929391926109259260018501920190610dd6565b5060408201518051610941916002840191602090910190610dd6565b506060820151805161095d916003840191602090910190610dd6565b5060808201518051610979916004840191602090910190610dd6565b5060a08201518051610995916005840191602090910190610dd6565b505050505050505050565b604080518082018252600160a060020a039384168082526020808301948552600091825260039052919091209051815473ffffffffffffffffffffffffffffffffffffffff1916931692909217825551600190910155565b6004545b90565b600160a060020a0381166000908152600260208181526040928390208201805484516001821615610100026000190190911693909304601f810183900483028401830190945283835260609390918301828280156106785780601f1061064d57610100808354040283529160200191610678565b6004805460018181019092557f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b018054600160a060020a0380891673ffffffffffffffffffffffffffffffffffffffff1992831681179093556040805160a08101825284815260208181018b81528284018b9052606083018a9052608083018990526000968752878252929095208151815494169390941692909217835551805191949293610b2793850192910190610dd6565b5060408201518051610b43916002840191602090910190610dd6565b5060608201518051610b5f916003840191602090910190610dd6565b5060808201518051610b7b916004840191602090910190610dd6565b5050505050505050565b600160a060020a03811660009081526001602081815260409283902060040180548451600294821615610100026000190190911693909304601f810183900483028401830190945283835260609390918301828280156106785780601f1061064d57610100808354040283529160200191610678565b6000816040516020018082805190602001908083835b60208310610c305780518252601f199092019160209182019101610c11565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b60208310610c935780518252601f199092019160209182019101610c74565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166001600085600160a060020a0316600160a060020a031681526020019081526020016000206004016040516020018082805460018160011615610100020316600290048015610d4a5780601f10610d28576101008083540402835291820191610d4a565b820191906000526020600020905b815481529060010190602001808311610d36575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310610d8d5780518252601f199092019160209182019101610d6e565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161415610dcc57506001610dd0565b5060005b92915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e1757805160ff1916838001178555610e44565b82800160010185558215610e44579182015b82811115610e44578251825591602001919060010190610e29565b50610e50929150610e54565b5090565b6109fc91905b80821115610e505760008155600101610e5a5600a165627a7a72305820e8bf218785bf6e50415ce00b8d0c4e130cad55ba797a3d20ab6c5226a0c4c2670029";

    public static final String FUNC_GETCANDIDATEPHONENUMBER = "getCandidatePhonenumber";

    public static final String FUNC_GETCANDIDATENAME = "getCandidateName";

    public static final String FUNC_GETCANDIDATENUMBEROFVOTES = "getCandidateNumberOfVotes";

    public static final String FUNC_GETCANDIDATECAMPAIGN = "getCandidateCampaign";

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
