package com.example.chefslaundary

class OrderList {

    /* Ingredient Id Label From Main Class
     Bread = 1  Turkey = 2  Bacon = 3  Chicken = 4  Cheese = 5  SweetPeppers = 6  Tomato = 7  Onion = 8  Lettuce = 9  Ketchup = 10  Mustard = 11  Mayo = 12
      */

    /* 4 Common Customer Orders */

    /* 1: Classic Turkey Sandwich: Two slices of bread, mayo and mustard on one side, add turkey and cheese slices, with lettuce and tomato.
                    Id Equivalent: [1,2,5,7,9,11,12]  */
    val order1 :String = " Classic Turkey Sandwich\n\n" +
            "Two slices of bread with mayo and mustard on one side.\n" +
            "Add turkey and cheese slices, with lettuce and tomato."
    val order1Id = mutableListOf<Int>(1,2,5,7,9,11,12)

    /* 2: BLT Sandwich: Two slices of bread with mayo on one side, add bacon, lettuce, sweet peppers, and tomato slices.
                   Id Equivalent: [1,3,7,9,12]  */
    val order2 :String = " BLT Sandwich\n\n" +
            "Two slices of bread with mayo on one side.\n" +
            "Add bacon, lettuce, sweet peppers, and tomato slices."
    val order2Id = mutableListOf<Int>(1,3,6,7,9,12)

    /* 3: Chicken Bacon Club Sandwich: Two slices of bread, add mayo on one side, add chicken, bacon, lettuce, onion, and tomato slices.
                   Id Equivalent: [1,3,4,7,8,9,10,12]  */
    val order3 :String = " Chicken Bacon Club Sandwich\n\n" +
            "Two slices of bread with mayo and ketchup on one side.\n" +
            "Add chicken, bacon, lettuce, onion, and tomato slices."
    val order3Id = mutableListOf<Int>(1,3,4,7,8,9,10,12)

    /* 4: Veggie and Cheese Sandwich: Two slices of bread, add mayo and mustard on one side, add cheese slices, sweet peppers, tomato, onion, and lettuce.
                  Id Equivalent: [1,5,6,7,8,9,11,12]  */
    val order4 :String = " Veggie and Cheese Sandwich\n\n" +
            "Two slices of bread with mayo and mustard on one side.\n" +
            "Add cheese slices, sweet peppers, tomato, onion, and lettuce."
    val order4Id = mutableListOf<Int>(1,5,6,7,8,9,11,12)

    /* 5: Monster Sandwich: Two slices of bread, add mayo, ketchup, and mustard on one side, add cheese slices with all the meats, sweet peppers, tomato, onion, and lettuce.
                Id Equivalent: [1,2,3,4,5,6,7,8,9,10,11,12]  */
    val order5 :String = " M-M-Monster Sandwich:\n\n" +
            "Two slices of bread, add mayo, ketchup, and mustard on one side.\n" +
            "Add cheese slices with all the meats, sweet peppers, tomato, onion, and lettuce."
    val order5Id = mutableListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12)

    /* 6: Feeling like a Carnivore Sandwich: Two slices of bread, add mayo, and mustard on one side, add cheese slices with turkey, bacon, and chicken.
                Id Equivalent: [1,2,3,4,5,11,12]  */
    val order6 :String = " Feeling like a Carnivore Sandwich:\n\n" +
            "Two slices of bread, add mayo, and mustard on one side.\n" +
            "Add cheese slices with turkey, bacon, and chicken."
    val order6Id = mutableListOf<Int>(1,2,3,4,5,11,12)

    /* 7: Rough Afternoon Sandwich: Two slices of bread, add mayo on one side, add cheese slices with turkey, and top it off with a raw onion.
                Id Equivalent: [1,2,5,8,12]  */
    val order7 :String = " Rough Afternoon Sandwich:\n\n" +
            "Two slices of bread, add mayo on one side.\n" +
            "Add cheese slices with turkey, and top it off with a raw onion."
    val order7Id = mutableListOf<Int>(1,2,5,8,12)

    /* 8: KING Caesar Kids Sandwich: Two slices of bread, add mayo and ketchup on one side, add cheese slices with chicken, sweet peppers, tomato, onion, and lettuce.
                Id Equivalent: [1,4,5,6,7,8,9,10,12]  */
    val order8 :String = " KING Caesar Kids Sandwich:\n\n" +
            "Two slices of bread, add mayo and ketchup on one side.\n" +
            "Add cheese slices with chicken, sweet peppers, tomato, onion, and lettuce.\n" +
            "Note: We don't have caesar dressing here."
    val order8Id = mutableListOf<Int>(1,4,5,6,7,8,9,10,12)

















}