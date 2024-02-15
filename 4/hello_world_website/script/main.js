function onFormClick(){
    //document.querySelector("form").style.background = "lavender";
    // if (document.querySelector("form").style.background == "lavender"){
    //     document.querySelector("form").style.background = "lime";
    // }
    // else{
    //     document.querySelector("form").style.background = "lavender";
    // }
    let fname = document.querySelector("#fname").value;
    console.log(fname)
    let lname = document.getElementById("lname").value;
    console.log(lname)

    document.getElementById("form-display").innerText = fname + " " + lname;
    
    let tableRef = document.querySelector("#name_table");
    let newRow = tableRef.insertRow(-1);

    let dataRef1 = newRow.insertCell(0)
    let dataRef2 = newRow.insertCell(1)

    dataRef1.innerText = fname;
    dataRef2.innerText = lname;
}