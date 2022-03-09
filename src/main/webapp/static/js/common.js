// $.querySelector(".buttons").addEventListener("click",event => {
//     console.log(event.target.style)
//     event.target.style="display";
// })
// console.log($.querySelector("#select_roles"));
// $.querySelector("#select_roles").addEventListener("change", event => {
//     event.preventDefault();
//
//     console.log("ERER")
//     let newRole = event.target.value;
//     $.post("http://localhost:8080/controller?command=editUserRole", {role: newRole});
// });
console.log("Yeeep")

document.querySelector("#forOpenEdit").addEventListener("click", event => {
    document.querySelector("#form_edit").style.display = "block";
    document.querySelector(".editor").style.display = "block";
    document.querySelector("#form_creator").style.display = "none";
})

document.querySelector("#forOpenCreateTask").addEventListener("click", event => {
    document.querySelector("#form_creator").style.display = "block";
    document.querySelector("#form_edit").style.display= "none";
})

