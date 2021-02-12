<?php
    include('db.php');  
  
  $fname=$_POST['fullname'];
  $mobile=$_POST['mobile'];
  $email=$_POST['email'];
  $age=$_POST['age'];
  $sex=$_POST['sex'];
  $pass=$_POST['password'];  

  
  $query="select * from user_worker where mobileno='$mobile'";  
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
          $query="insert into user_worker (fullname,mobileno,emailid,password,sex,age) values ('$fname','$mobile','$email','$pass','$sex','$age');";
          mysqli_query($conn,$query);
          echo trim("Worker Registered Successfully");
        }
 
?>  