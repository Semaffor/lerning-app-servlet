const openModalArray = document.querySelectorAll("#myModal");

document.querySelectorAll(".openModal").forEach((item, i) => {
    item.addEventListener("click", event => {
        openModalArray[i].style = "display: block";
    })
})

document.querySelectorAll("#closeModal").forEach((item, i) => {
    item.addEventListener("click", event => {
        openModalArray[i].style = "display: none";
    })
})

document.querySelector("#forOpenEdit").addEventListener("click", event => {
    document.querySelector(".notification_wrapper").style.display = "none";
    document.querySelector("#form_edit").style.display = "block";
    document.querySelector("#form_creator").style.display = "none";
})

document.querySelector("#forOpenCreateTask").addEventListener("click", event => {
    document.querySelector(".notification_wrapper").style.display = "none";
    document.querySelector("#form_creator").style.display = "block";
    document.querySelector("#form_edit").style.display = "none";
})


