package blockchain;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class MyChain{
public static ArrayList<Block> blockchain = new ArrayList<Block>();
public static int difficulty = 5;

	public static void main(String[] args) {
		//add our blocks to the blockchain ArrayList:
		blockchain.add(new Block("Hi im the first block", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);

		blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);

		blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);

		System.out.println("\nBlockchain is Valid: " + isChainValid());

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}


   public static Boolean isChainValid(){
       Block currentBlock;
       Block previousBlock;

       for(int i = 1 ; i < blockchain.size() ; i++ ){
                currentBlock = blockchain.get(i);
                previousBlock = blockchain.get(i-1);
                //comparing current hash values
                if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                    System.out.println("Current hashes are not equal");
                    return false;
                }
                //comparing previous block hash with current one
                if(!previousBlock.hash.equals(currentBlock.previousHash)){
                    System.out.println("Previous Hashes not equal");
		        	return false;
                }
       }
       return true;
   }

}