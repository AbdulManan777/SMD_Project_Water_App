<?php
#   http://localhost/smdass3/insert.php
include "conn.php";
$res=array();
if(isset($_POST['username'],$_POST['bedtime']))
{
    $name=$_POST['username'];
    $bedtime=$_POST['bedtime'];
    //$password=$_POST['Password'];
    $query="INSERT INTO `bedtimings`
    (`username`, `bedtime`) 
    VALUES ('$name','$bedtime')";
    $r=mysqli_query($con,$query);

    if($r)
    {
        $res["msg"]="Bed timing Inserted";
        $res["code"]=1;
        $res["id"]=mysqli_insert_id($con);
    }
    else{
        $res["msg"]="Failed to Insert";
        $res["resultcode"]=-1;
        $res["id"]="N/A";

    }
}
else{
    $res["msg"]="incomplete Request";
    $res["resultcode"]=-1;
    $res["id"]="N/A";


}

$x=json_encode($res);
echo $x;
?>