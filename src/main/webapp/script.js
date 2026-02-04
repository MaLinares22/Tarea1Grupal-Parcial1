//Funcion para ocultar o mostrar los formularios al seleccionar los botones
function mostrar(seccionId){
    const secciones = document.querySelectorAll('.seccion');
    	secciones.forEach(seccion => {
        seccion.classList.add('oculto');
	});
    
	const seccionActiva = document.getElementById(seccionId);
		if(seccionActiva) {
		seccionActiva.classList.remove('oculto');
	}
    
	const botones = document.querySelectorAll('.menu button');
    	botones.forEach(boton => {
        boton.classList.remove('activo');
	});
    
	const botonActivo = document.querySelector(`button[data-target="${seccionId}"]`);
		if(botonActivo) {
		botonActivo.classList.add('activo');
    }
}

//Funcion para cambiar el espacio de numeros a rellenar al momento de cambiar de formulario
function toggleNumeros() {
    const select = document.getElementById('Seleccion');
    const tres = document.getElementById('tresNumeros');
    const muchos = document.getElementById('muchosNumeros');
    
    if(!select || !tres || !muchos) return;
    
    if(select.value === 'repetido') {
        tres.style.display = 'none';
        muchos.style.display = 'block';
        
        document.getElementById('num1').removeAttribute('required');
        document.getElementById('num2').removeAttribute('required');
        document.getElementById('num3').removeAttribute('required');
        document.getElementById('numbers').setAttribute('required', 'required');
        
    } else{
        tres.style.display = 'block';
        muchos.style.display = 'none';
        
        document.getElementById('num1').setAttribute('required', 'required');
        document.getElementById('num2').setAttribute('required', 'required');
        document.getElementById('num3').setAttribute('required', 'required');
        document.getElementById('numbers').removeAttribute('required');
    }
}

//Funcion para validar el ingreso de de los datos a el formulario de mayores, menores y repetidos
function validarFormulario() {
    const select = document.getElementById('Seleccion');
    	if(select.value === 'maximo' || select.value === 'minimo') {
	        const num1 = document.getElementById('num1').value.trim();
    	    const num2 = document.getElementById('num2').value.trim();
        	const num3 = document.getElementById('num3').value.trim();
        
        	if(num1 === '' || num2 === '' || num3 === '') {
            	alert('Por favor, ingresa los 3 números para calcular el ' + 
                	(select.value === 'maximo' ? 'mayor' : 'menor'));
	            return false;
			}
		}else if(select.value === 'repetido') {
        	const numbers = document.getElementById('numbers').value.trim();
        	if (numbers === '') {
            	alert('Por favor, ingresa números separados por coma');
            	return false;
			}
    	}
	return true; 
}

//Mostrar Binarios al cargar la pagina
window.onload = function() {
    toggleNumeros();
    mostrar('binarios');
};
