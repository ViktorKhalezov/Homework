package com.geekbrains.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    private ServerSocketChannel serverChannel;
    private Selector selector;
    private ByteBuffer buf;
    private Path path;

    public NioServer(int port) throws IOException {
        path = Paths.get("server_files");
        buf = ByteBuffer.allocate(5);
        serverChannel = ServerSocketChannel.open();
        selector = Selector.open();
        serverChannel.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (serverChannel.isOpen()) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            try {
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        handleAccept();
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    iterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        StringBuilder msg = new StringBuilder();
        while (true) {
            int read = channel.read(buf);
            if (read == -1) {
                channel.close();
                return;
            }
            if (read == 0) {
                break;
            }
            buf.flip();
            while (buf.hasRemaining()) {
                msg.append((char) buf.get());
            }
            buf.clear();
        }
        processMessage(channel, msg.toString());
        // String response = "Hello " + msg + key.attachment();
        // channel.write(ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8)));
    }


    private void processMessage(SocketChannel channel, String msg) throws IOException {
        // TODO: 29.11.2021
        String[] msgArray = msg.substring(0, msg.length() - 2).split(" ");
        String fileName = "";
        String msgDir = "";
        Path pathToFile = null;
        Path parentDir = null;
        String message = "";
        String command = msgArray[0];
        switch(command) {
            case("ls"):
                if (msgArray.length == 1) {
                    Files.list(path).forEach(file -> {
                        try {
                            channel.write(ByteBuffer.wrap((file + "\n").getBytes(StandardCharsets.UTF_8)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                    try {
                    msgDir = receivePathFromMessage(msgArray);
                    Path msgPath = Paths.get(msgDir);
                    Files.list(msgPath).forEach(file -> {
                        try {
                            channel.write(ByteBuffer.wrap((file + "\n").getBytes(StandardCharsets.UTF_8)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    }catch(NoSuchFileException e) {
                        message = "Directory doesn't exist\n";
                        channel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
                    }
                }
                break;
            case("cat"):
                fileName = receivePathFromMessage(msgArray);
                if(fileName.contains("\\")) {
                        transmitDataFromFile(channel, fileName);
                } else {
                        String filePathString = path + "\\" + fileName;
                        Path fileNamePath = Paths.get(filePathString);
                        if(Files.exists(fileNamePath)){
                        transmitDataFromFile(channel, filePathString);
                    } else {
                    message = "File doesn't exist\n";
                    channel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
                    }
                }
                break;
            case("cd"):
                msgDir = receivePathFromMessage(msgArray);
                Path newCurDir = Paths.get(msgDir);
                if(Files.exists(newCurDir)){
                    path = newCurDir;
                    message = "New current directory: " + path + "\n";
                } else {
                    message = "Directory is not found\n";
                }
                channel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
                break;
            case("touch"):
                fileName = receivePathFromMessage(msgArray);
                try {
                    if (fileName.contains("\\")) {
                        parentDir = Paths.get(fileName).getParent();
                        if (Files.exists(parentDir)) {
                            pathToFile = Paths.get(fileName);
                            Files.createFile(pathToFile);
                        } else {
                            message = "Parent directory doesn't exist\n";
                            channel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
                        }
                    } else {
                        pathToFile = Paths.get(path + "\\" + fileName);
                        Files.createFile(pathToFile);
                    }
                } catch(FileAlreadyExistsException e) {
                    message = "File already exists\n";
                    channel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
                }
                break;
            case("mkdir"):
                msgDir = receivePathFromMessage(msgArray);
                Path pathToNewDir = Paths.get(msgDir);
                try {
                    if (msgDir.contains("\\")) {
                        parentDir = Paths.get(msgDir).getParent();
                        if (Files.exists(parentDir)) {
                            Files.createDirectory(pathToNewDir);
                        } else {
                            message = "Parent directory doesn't exist\n";
                            channel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
                        }
                    } else {
                        pathToNewDir = Paths.get(path + "\\" + msgDir);
                        Files.createDirectory(pathToNewDir);
                    }
                } catch(FileAlreadyExistsException e) {
                    message = "Directory already exists\n";
                    channel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
                }
                break;
            default:
            String response = "Invalid command\n";
            channel.write(ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8)));
            break;
        }

    }

    private void handleAccept() throws IOException {
        System.out.println("Client accepted...");
        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ, "Hello world!");
    }

    private String receivePathFromMessage(String[] msgArray) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < msgArray.length; i++) {
            sb.append(msgArray[i]).append(" ");
        }
        return sb.toString().trim();
    }


    private void transmitDataFromFile(SocketChannel socketChannel, String msgPath) throws IOException {
        buf.clear();
        try (RandomAccessFile raf = new RandomAccessFile(msgPath, "rw")) {
            FileChannel fileChannel = raf.getChannel();
            while(fileChannel.read(buf) != -1 ) {
                buf.flip();
               while (buf.hasRemaining()) {
                    socketChannel.write(buf);
                }
                buf.clear();
            }
            fileChannel.close();
            socketChannel.write(ByteBuffer.wrap("\n".getBytes(StandardCharsets.UTF_8)));
        } catch (FileNotFoundException e) {
            String message = "File doesn't exist\n";
            socketChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
        }
    }


    public static void main(String[] args) throws IOException {
        new NioServer(8189);
    }

}



/*    RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
    FileChannel inChannel = aFile.getChannel();
    ByteBuffer buf = ByteBuffer.allocate(48);
    int bytesRead = inChannel.read(buf);
    while (bytesRead != -1) {
            buf.flip();
            while(buf.hasRemaining()){
            System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
            }
            aFile.close(); */

// C:\Users\vikto\Desktop

// C:\Users\vikto\Desktop\result.txt

// C:\Users\vikto\Desktop\project