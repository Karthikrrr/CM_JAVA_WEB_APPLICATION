console.log("HEllo");
const viewContactModel = document.getElementById("view_contact_model");

const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_model',
  override: true
};

const contactModal = new Modal(viewContactModel,options,instanceOptions);

function openContactModal() {
       contactModal.show();
}

function closeContactModal(){
        contactModal.hide();
}

async function loadUserData(id){
        try{
         console.log(id);
               const data = await ( await fetch(`http://localhost:8081/api/contacts/${id}`)).json();
                document.querySelector("#contact_name").innerHTML = data.name;
                document.querySelector("#contact_email").innerHTML = data.email;
                openContactModal();
        } catch(err){
         console.log(err);
        }


}