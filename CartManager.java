package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@Scope("prototype")
public class CartManager {
    private String command;

    @Autowired
    private Cart cart;

    public CartManager(Cart cart) {
        this.cart = cart;
        new Thread( () -> {
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    System.out.println("Вас приветствует консольное управление корзиной");
                    System.out.println();
                    helpCommand();
                    System.out.println("Товары, доступные для добавления в корзину:");
                    cart.showRepositoryProducts();
                    System.out.println("Введите команду:");
                    while (!(command = reader.readLine()).equals("exit")) {
                        if(command.equals("show c")) {
                            cart.showProductsInCart();
                        } else if (command.equals("show r")) {
                            cart.showRepositoryProducts();
                        } else if (command.startsWith("add")) {
                            if(command.equals("add all")) {
                                cart.addAllProducts();
                            } else {
                                if(command.split(" ").length == 2) {
                                    String possibleId = command.split(" ")[1];
                                    if (isDigit(possibleId) == true) {
                                        int id = Integer.parseInt(possibleId);
                                        cart.addProduct(id);
                                    } else {
                                        System.out.println("Введен некорректный id");
                                    }
                                } else {
                                    System.out.println("Введена некорректная команда");
                                }
                            }
                        } else if(command.startsWith("del")) {
                            if(command.split(" ").length == 2) {
                                String possibleId = command.split(" ")[1];
                                if (isDigit(possibleId) == true) {
                                    int id = Integer.parseInt(possibleId);
                                    cart.deleteProduct(id);
                                } else {
                                    System.out.println("Введен некорректный id");
                                }
                            } else {
                                System.out.println("Введена некорректная команда");
                            }
                        } else if(command.equals("clean")) {
                            cart.cleanCart();
                        } else if(command.startsWith("ch")) {
                            if(command.split(" ").length == 3) {
                                String possibleId = command.split(" ")[1];
                                String possibleQuantity = command.split(" ")[2];
                                if(isDigit(possibleId) == true && isDigit(possibleQuantity) == true && Integer.parseInt(possibleQuantity) > 0) {
                                    int id = Integer.parseInt(possibleId);
                                    int quantity = Integer.parseInt(possibleQuantity);
                                    cart.changeQuantity(id, quantity);
                                } else {
                                    System.out.println("Введена некорректная команда");
                                }
                            } else {
                                System.out.println("Введена некорректная команда");
                            }
                        } else if(command.equals("sum")) {
                            System.out.println("Сумма корзины: " + cart.getSum());
                        } else if(command.equals("help")) {
                            helpCommand();
                        } else {
                            System.out.println("Введена некорректная команда");
                        }
                        System.out.println();
                        System.out.println("Введите команду:");
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
        }).start();
    }


    public void helpCommand() {
        System.out.println("Для работы с корзиной введите в консоль одну из команд:");
        System.out.println("Показать товары в корзине: show c");
        System.out.println("Показать доступные для добавления товары: show r");
        System.out.println("Добавить товар в корзину: add id (пример использования: add 1");
        System.out.println("Добавить в корзину все доступные товары: add all");
        System.out.println("Удалить товар из корзины: del id (пример использования: del 3");
        System.out.println("Удалить все товары из корзины: clean");
        System.out.println("Поменять количество товара в корзине: ch id quantity (пример использования: ch 3 5");
        System.out.println("Посчитать сумму товаров в корзине: sum");
        System.out.println("Получить список возможных команд: help");
        System.out.println("Завершить работу с корзиной: exit");
        System.out.println();
    }


    private boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
