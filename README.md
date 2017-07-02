# K-LAB
Проекты лабораториии робототехники и мехатроники КубГУ (https://vk.com/kubsu.k_lab).


## Arduino

https://github.com/mrsin/Dynamixel_Test
Управляет и читает данные с сервы SPRINGRC SR508H по протоколу Dynamixel на Arduino Mega через библиотеку DynamixelSerial1.

https://github.com/mrsin/Arduino_Serial_Pipe
Пересылает данные из первого последовательного порта  Serial1 (RX1/TX1 на Arduino Mega) в USB Serial и обратно.


## STM32

https://github.com/mrsin/Blink_CubeMX_STM32F429I
Инициализирует периферию и мигает светодиодом с помощью библиотеки HAL.

https://github.com/mrsin/STM32F429I_ITG-3200_ADXL345
Читаем сырые данные с акселерометра ADXL345 и гироскопа ITG 3200 по протоколу I2C с помощью библиотеки HAL.

То же самое с использованием DMA.
https://github.com/mrsin/STM32F429I_ITG-3200_ADXL345_DMA

https://github.com/mrsin/STM32F429I_UART_DMA_loop
Получаем и отправляем данные через UART с использованием DMA.

https://github.com/mrsin/STM32F3_Encoder
Чтение квадратурного энкодера аппаратным таймером STM32F303 + картинки настройки таймера в CubeMX.

https://github.com/alus96/STM32CAN
Передача данных по CAN в режиме петли (данные передаются и принимаются внутри микроконтроллера) на STM32F407

## Android

https://github.com/mrsin/AccelGyro
Работа с акселерометром, гироскопом и вибро на Android.

https://github.com/mrsin/TextToSpeech
Преобразование текста в речь на русском языке для Android.

https://github.com/alexanzer/android/tree/master/K-lab
Клиент соединяется с устройством (киберперчаткой Карины) по Bluetooth и озвучивает получаемые команды с помощью синтеза речи на русском языке.
Сервер на другом телефоне может принять подключение и отправлять команды, вместо микроконтроллера с Bluetooth

https://github.com/mrsin/RobotCommander
Программа для управления роботом по Bluetooth.
