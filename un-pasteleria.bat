@echo off
title Lanzador Maestro de Microservicios - Pasteleria
echo =====================================================================
echo     INICIANDO ECOSISTEMA COMPLETO DE MICROSERVICIOS (PASTELERIA)
echo =====================================================================
echo.

:: 1. INFRAESTRUCTURA BASE: EUREKA SERVER
echo [01/13] Levantando Eureka Server (Puerto 8761)...
cd eureka-service
start "Eureka Server" cmd /k mvnw.cmd spring-boot:run
cd ..
echo Esperando a que Eureka inicialice...
timeout /t 15 /nobreak > nul

:: 2. INFRAESTRUCTURA BASE: CONFIG SERVER
echo [02/13] Levantando Config Server (Puerto 8888)...
cd config-server
start "Config Server" cmd /k mvnw.cmd spring-boot:run
cd ..
echo Esperando a que Config Server cargue las propiedades centrales...
timeout /t 12 /nobreak > nul

:: 3. MICROSERVICIOS DE NEGOCIO (Todos en Puerto 0 Dinamico)
echo [03/13] Levantando Administrador Service...
cd administrador-service
start "Administrador Service" cmd /k mvnw.cmd spring-boot:run
cd ..

echo [04/13] Levantando Chofer Service...
cd chofer-service
start "Chofer Service" cmd /k mvnw.cmd spring-boot:run
cd ..

echo [05/13] Levantando Cliente Service...
cd cliente-service
start "Cliente Service" cmd /k mvnw.cmd spring-boot:run
cd ..

echo [06/13] Levantando Despacho Service...
cd despacho-service
start "Despacho Service" cmd /k mvnw.cmd spring-boot:run
cd ..

echo [07/13] Levantando Evento Service...
cd evento-service
start "Evento Service" cmd /k mvnw.cmd spring-boot:run
cd ..

echo [08/13] Levantando Receta Service...
cd receta-service
start "Receta Service" cmd /k mvnw.cmd spring-boot:run
cd ..

echo [09/13] Levantando Sucursal Service...
cd sucursal-service
start "Sucursal Service" cmd /k mvnw.cmd spring-boot:run
cd ..

echo [10/13] Levantando Vehiculo Service...
cd vehiculo-service
start "Vehiculo Service" cmd /k mvnw.cmd spring-boot:run
cd ..

echo [11/13] Levantando Vendedor Service...
cd vendedor-service
start "Vendedor Service" cmd /k mvnw.cmd spring-boot:run
cd ..

echo.
echo Esperando a que todos los servicios levanten sus bases de datos
echo y se registren exitosamente en el panel de Eureka...
timeout /t 15 /nobreak > nul

:: 4. PUERTA DE ENTRADA: API GATEWAY
echo [12/13] Levantando API Gateway (Puerto 8080)...
cd api-gateway
start "API Gateway" cmd /k mvnw.cmd spring-boot:run
cd ..

echo.
echo =====================================================================
echo   [13/13] ¡PROCESO DE ARRANQUE COMPLETO EN EJECUCION!
echo =====================================================================
echo   * Revisa qué ventanas se quedaron abiertas con errores.
echo =====================================================================
echo.
pause