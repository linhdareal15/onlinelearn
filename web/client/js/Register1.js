/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let patternUsername = "^[a-zA-Z][a-z0-9_-]{4,}$";
let patternName = "^[A-Z a-z]+$";
let patternPassWord = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
let patternEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$"; 
let patternPhone = "^09[0-9]{8}$";
let patternAddress = "^[A-Z0-9 a-z,/_-]+$";

function Validate(inputElement,rule){
    var errorElement = inputElement.parentElement.querySelector('.form-message');
    var success = inputElement.parentElement.querySelector('.success');
    var error = inputElement.parentElement.querySelector('.error');
    var errorMessage = rule.test(inputElement.value); 
    if(errorMessage){
        let message = errorMessage;
        console.log(errorMessage);
        inputElement.setCustomValidity(message);
        inputElement.reportValidity();
        error.style.visibility = "visible";
        success.style.visibility = "hidden";
        
    }
    else{
        error.style.visibility = "hidden";
        success.style.visibility = "visible";
        inputElement.setCustomValidity("");
        inputElement.reportValidity();
    }
}

function Validator(options){
    var formElement = document.querySelector(options.form);
    if(formElement){
        options.rules.forEach(function (rule) {
            var inputElement = formElement.querySelector(rule.selector);
            
            if(inputElement){
                inputElement.onblur = function (){
                    Validate(inputElement,rule);        
                };
            }
        });
    }
}

Validator.isName = function(selector,){
    return {
        selector: selector,
        test: function (value){
            var error;
            value.trim();
            if(value.length === 0){
               error = 'Please enter your name';
            }
            else if(!value.match(patternName)){
                error = "Name is invalid";
            }
            else{
                error = undefined;
            }
//            return value.trim() ? undefined : "Please enter your name!";
            return error;
        }
    };
};

Validator.isEmail = function(selector){
    return {
        selector: selector,
        test: function (value){
            var error;
            value.trim();
            if(value.length === 0){
               error = 'Please enter your mail';
            }
            else if(!value.match(patternEmail)){
                error = "Email is invalid";
            }
            else{
                error = undefined;
            }
//            return value.trim() ? undefined : "Please enter your name!";
            return error;
        }
     };
};


Validator.isUserName = function(selector){
    return {
        selector: selector,
        test: function (value){
            
            var error;
            value.trim();
            if(value.length === 0){
               error = 'Please enter username';
            }
            else if(!value.match(patternUsername)){
                error = "Username must start with lower character,may be contain digit and at least 4 character";
            }
            else{
                error = undefined;
            }
//            return value.trim() ? undefined : "Please enter your name!";
            return error;
        }
     };
};

Validator.isPassWord = function(selector){
    return {
        selector: selector,
        test: function (value){
            var error;
            value.trim();
            if(value.length === 0){
               error = 'Please enter your password';
            }
            else if(!value.match(patternPassWord)){
                error = "Password must contain minimum 8 characters, at least one uppercase letter, one lowercase letter and one digit and one special characters";
            }
            else{
                error = undefined;
            }
//            return value.trim() ? undefined : "Please enter your name!";
            return error;
        }
     };
};

Validator.confirmPassword = function(selector){
    return {
        selector: selector,
        test: function (value){
            var error;
            value.trim();
            if(value.length === 0){
               error = 'Please reenter password';
            }
            else if(value !== document.getElementById('password').value){
                error = "Password is not match";
            }
            else{
                error = undefined;
            }
//            return value.trim() ? undefined : "Please enter your name!";
            return error;
        }
     };
};


Validator.isAddress = function(selector){
    return {
        selector: selector,
        test: function (value){
            var error;
            value.trim();
            if(value.length === 0){
               error = 'Please enter your adress';
            }
            else if(!value.match(patternAddress)){
                error = "Adress is invalid";
            }
            else{
                error = undefined;
            }
//            return value.trim() ? undefined : "Please enter your name!";
            return error;
        }
     };
};


Validator.isPhone = function(selector){
    return {
        selector: selector,
        test: function (value){
            var error;
            value.trim();
            if(value.length === 0){
               error = 'Please enter your phone number';
            }
            else if(!value.match(patternPhone)){
                error = "Phone is invalid";
            }
            else{
                error = undefined;
            }
//            return value.trim() ? undefined : "Please enter your name!";
            return error;
        }
     };
};
