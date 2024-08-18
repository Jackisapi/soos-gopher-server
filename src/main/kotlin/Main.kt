package xyz.chilll

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers

suspend fun main() {
    val selectorManager = SelectorManager(Dispatchers.IO)


    val ip = "127.0.0.1"
    // localhost by default

    val port = 7000
    //gopher is usually at post 70 but on Unix and Linux based systems ports under 1024 are reserved unless you are Root

    val serverSocket = aSocket(selectorManager).tcp().bind("127.0.0.1",7000)
    //Creates A Socket at 12.0.0.1 at Port 7000

    while (true) {
        // Allows Client to accept Socket
        val socket = serverSocket.accept()


        println("server Connected gopher://$ip:$port")


        val sendChannel = socket.openWriteChannel(autoFlush = true)
        // Sets up the send channel this is where the server will send data to the client

        val receiveChannel = socket.openReadChannel()
        // Receive channel this is for Client to Server

        val message = receiveChannel.readByte()

        val finalMessage = message.toInt().toChar()
        //Converts the message to a character in this case a  (Note change code to allow full strings later)

        println("Server Sent $finalMessage")

        val testMessage = "iThis is a test message \n"
        // i is the gopher formatting to a standard string think of it like the <p> tag

        val testMessage2 = "iDeez Nuts \n"

        println("Responding with details $testMessage")

        sendChannel.writeFully(testMessage.toByteArray(charset("utf-8")))
        // converts the messages from above to byte arrays in utf-8
        sendChannel.writeFully(testMessage2.toByteArray(charset("utf-8")))

    }
}

