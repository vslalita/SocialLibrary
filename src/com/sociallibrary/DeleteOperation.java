package com.sociallibrary;

public class DeleteOperation implements BookOperationRequest {
    private int id;
    
    public DeleteOperation(int id){
    	this.id=id;
    }
    
	@Override
	public void execute() {
		// TODO Auto-generated method stub
        bo.deleteBook(id);
	}

}
