<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="bootstrap">
	<tiles:putAttribute name="content">
    
    	<div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><small>To create a new Process of Service, enter all required</h3>
            </div>
        </div>
        
        <div class="row">
            <div class="col-lg-12">
            	<div class="panel panel-default">
                	<div class="panel-heading panel-heading-custom">Client Details</div>
                    <div class="panel-body">
                    	<div class="row">
                        	<div class="col-lg-6">
                            	<form role="form">
	                                <div class="form-group">
	                                    <label>Matter Id:</label>
	                                    <input class="form-control" disabled>
	                                </div>
                                   	<div class="form-group">
                                    	<label>Primary Contact: *</label>
                                            <select class="form-control">
                                            	<option>Please Select ...</option>
                                                <option>Jameson, James Esq.</option>
                                                <option>Eastin, Jeffrey Esq.</option>
                                                <option>Williams-Oxley, Melissa</option>
                                                <option>Goldstein, Timothy</option>
                                                <option>McLaren, Vanessa Esq.</option>
                                            </select>
                                   	</div>
                               	</form>
                    		</div>
                                <!-- /.col-lg-6 (nested) -->
                           	<div class="col-lg-6">
                            	<form role="form">
                                	<div class="form-group">
                                    	<label>Company: *</label>
                                    	<select class="form-control">
                                         	<option>Please Select ...</option>
                                             <option>Boies, Schiller & Flexner LLP</option>
                                             <option>Cravath, Swaine & Moore LLP</option>
                                             <option>Fried, Frank, Harris, Shriver & Jacobson LLP</option>
                                             <option>Gibson, Dunn & Crutcher LLP</option>
                                             <option>Goldstein, Timothy</option>
                                             <option>Sullivan & Cromwell LLP</option>
                                             <option>Willkie Farr & Gallagher LLP</option>
                                        </select>
                              		</div>
                                         
                                  	<div class="form-group">
                                        <label>Date to be completed: *</label>
                                        <input class="form-control" >
                                    </div>
                               </form>
                          	</div>
                    	</div>
                   </div>
              </div>
         </div>
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading panel-heading-custom">Service Details</div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form">
                                        <div class="form-group">
                                            <label>Court Filing Required: *</label>
                                            <label class="radio-inline">
                                                <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="Yes" checked>Yes
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline2" value="No">No
                                            </label>
                                        </div>
                                       <div class="form-group">
                                            <label>Case Number: </label>
                                            <input class="form-control" >
                                        </div>
                                        <div class="form-group">
                                            <label>Case Caption: *</label>
                                            <textarea class="form-control" rows="3"></textarea>
                                        </div>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <form role="form">
                                     <div class="form-group">
                                            <label>Plaintiff: *</label>
                                           <input class="form-control" >
                                        </div>
                                        
                                         
                                        <div class="form-group">
                                            <label>Defendant: *</label>
                                      		<input class="form-control" >
                                        </div>
                                   </form>
                                    
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading panel-heading-custom">Service Additional Details</div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form">
                                       <div class="form-group">
                                            <label>Documents:</label>
                                            <input type="file">
                                        </div>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <form role="form">
                                        <div class="form-group">
                                            <label>Special Instructions:</label>
                                            <textarea class="form-control" rows="3"></textarea>
                                        </div>
                                         
                                       
                                   </form>
                                    
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <div class="form-group">
			    <label class="col-lg-12 control-label" for="singlebutton"></label>
			    <div class="col-lg-12">
			        <div class="col-lg-12 text-center"> 
						<button id="singlebutton" name="singlebutton" class="btn btn-primary">Save</button> 
						<button id="singlebutton" name="singlebutton" class="btn btn-primary">Process</button> 
						<button id="singlebutton" name="singlebutton" class="btn btn-primary">Cancel</button> 
					</div>
			    </div>
			</div>
			
			<div class="row">
                <div class="col-lg-12">
                    <h1>&nbsp;</h1>
                </div>
            </div>
   
	</tiles:putAttribute>
</tiles:insertDefinition>