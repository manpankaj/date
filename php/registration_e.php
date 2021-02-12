<?php
    include('db.php');  
  
  $fname=$_POST['empname'];
  $mobile=$_POST['mobile'];
  $email=$_POST['email'];
  $add=$_POST['add'];
  $pass=$_POST['password'];  

  
  $query="select * from user_employer where mobileno='$mobile'";  
        $result=mysqli_query($conn,$query);
    if(mysqli_num_rows($result)>0)
    {  
           while($row=mysqli_fetch_array($result))
          {
            echo trim("Mobile No Already Exist");
          }
    }
        else
        {
          $query="insert into user_employer (empname,mobileno,password,emailid,address) values ('$fname','$mobile','$pass','$email','$add');";
          mysqli_query($conn,$query);
          echo trim("Employer Registered Successfully");
        }
 
?>  