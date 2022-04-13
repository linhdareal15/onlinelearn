/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function doChangeStatus(id, status) {
    var c = confirm("Change Status ?");
    if (c) {
        window.location.href = "changestatus?id=" + id + "&status=" + status;
    }
}

function doChangeStatusSubject(id, status) {
    var c = confirm("Change Status ?");
    if (c) {
        window.location.href = "changestatusSubject?subject_id=" + id + "&subject_status=" + status;
    }
}

