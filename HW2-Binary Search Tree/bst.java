//Siying Li, CSE373A, Winter 2015, Homework 3
//This class implements a binary search tree and uses it to store and retrieve articles. It uses keywords to search for 
//articles.
class bst{
    
    Node root;
    
    private class Node{
    	
    	// These attributes of the Node class will not be sufficient for those attempting the AVL extra credit.
    	// You are free to add extra attributes as you see fit, but do not remove attributes given as it will mess with help code.
        String keyword;
        Record record;
        int size;
        Node l;
        Node r;

        //Initializes a node with the keyword and record passed in
        private Node(String k, Record recordToAdd){
        	this.keyword = k;
        	l = null;
        	r = null;
        	this.update(recordToAdd);
        }
        
        //Adds the record passed in to the front of the linked list of records
        private void update(Record r){
        	if(r==null) {
        		r = record;
        	} else {
	        	r.next = record;
	        	record = r;
        	}
        }
    }
    
    public bst(){
        this.root = null;
    }
      
    //Adds a Filedata passed in to the list of records for the node associated with keyword
    //If there is no node, it will add the node.
    public void insert(String keyword, FileData fd){
        Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);
        root = insert(keyword, root, recordToAdd);
    }
    
    //the recursive method that helps with adding a record for the node associated with keyword
    private Node insert(String keyword, Node root, Record recordToAdd) {
    	if(root == null) {
    		return new Node(keyword, recordToAdd);
    	}
    	
    	int compareResult = keyword.compareTo(root.keyword);
    	
    	if(compareResult == 0) {
    		root.update(recordToAdd);
    		return root;
    	} else if(compareResult < 0) {
    		root.l = insert(keyword, root.l, recordToAdd);
    	} else{
	    	root.r = insert(keyword, root.r, recordToAdd);
    	} 
    	
    	return root;
    }
    
    //Returns true or false indicating whether a particular keyword exists in the data structure
    public boolean contains(String keyword){
    	return contains(keyword, root);
    }
    
    //the recursive function to help with checking whether a keyword exists in the data structure
    private boolean contains(String keyword, Node root) {
    	if(root == null) {
    		return false;		
    	}
    	int compareResult = keyword.compareTo(root.keyword);
    	
    	if(compareResult == 0) {
    		return true;
    	} else if(compareResult < 0) {
    		return contains(keyword, root.l);
    	} else {
    		return contains(keyword, root.r);
    	}
    }

    //Returns the first record for a particular keyword
    //will return null if the keyword is not in the data structure
    public Record get_records(String keyword){
    	if(!contains(keyword)) {
    		return null;
    	} else {
    		return get_records(root, keyword);
    	}
    }
    
    //the recursive function to help with finding a record for a particular keyword
    private Record get_records(Node root, String keyword) {
    	int compareResult = keyword.compareTo(root.keyword);
    	if(compareResult == 0) {
    		return root.record;
    	} 
    	if(compareResult < 0) {
    		return get_records(root.l, keyword);
    	} else {
    		return get_records(root.r, keyword);
    	}
    }
    
    //removes the node with keyword from the data structure
    //will do nothing if the keyword does not exist in the tree
    public void delete(String keyword){
    	if(contains(keyword)) {
    		root = delete(keyword, root);
    	}
    }
    
    //the recursive function that helps with deleting a node in the tree
    private Node delete(String keyword, Node root) {
      if(root == null) {
         return null;
      }
      int compareResult = keyword.compareTo(root.keyword);
      if(compareResult < 0) {
         root.l = delete(keyword, root.l);
      } else if(compareResult > 0) {
         root.r = delete(keyword, root.r);
      } else {
         if((root.l != null) && (root.r != null)) {
            root.keyword = findMin(root.r).keyword;
            root.record = findMin(root.r).record;
            root.r = delete(root.keyword, root.r);
         } else if(root.l != null) {
        	root.record = root.l.record;
            root = root.l;
         } else if(root.r != null) {
        	root.record = root.r.record;
            root = root.r;
         } else {
            root = null;
         }
      }
      return root;
    }
    
    //returns the node with the smallest keyword value in the tree
    private Node findMin(Node root) {
      if(root == null) {
         return null;
      }else if(root.l == null) {
         return root;
      }
      return findMin(root.l);
    }
    
   

    public void print(){
        print(root);
    }

    private void print(Node t){
        if (t!=null){
            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;
            while(r != null){
                System.out.printf("\t%s\n",r.title);
                r = r.next;
            }
            print(t.r);
        } 
    }
    

}
