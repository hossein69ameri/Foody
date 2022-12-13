package com.example.nourifoodapp1.data.model


import com.google.gson.annotations.SerializedName

data class ResponseFood(
    @SerializedName("number")
    val number: Int?, // 1
    @SerializedName("offset")
    val offset: Int?, // 0
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("totalResults")
    val totalResults: Int? // 667
) {
    data class Result(
        @SerializedName("aggregateLikes")
        val aggregateLikes: Int?, // 3689
        @SerializedName("analyzedInstructions")
        val analyzedInstructions: List<AnalyzedInstruction?>?,
        @SerializedName("cheap")
        val cheap: Boolean?, // false
        @SerializedName("cookingMinutes")
        val cookingMinutes: Int?, // -1
        @SerializedName("creditsText")
        val creditsText: String?, // Full Belly Sisters
        @SerializedName("cuisines")
        val cuisines: List<String?>?,
        @SerializedName("dairyFree")
        val dairyFree: Boolean?, // true
        @SerializedName("diets")
        val diets: List<String?>?,
        @SerializedName("dishTypes")
        val dishTypes: List<String?>?,
        @SerializedName("extendedIngredients")
        val extendedIngredients: List<ExtendedIngredient?>?,
        @SerializedName("gaps")
        val gaps: String?, // no
        @SerializedName("glutenFree")
        val glutenFree: Boolean?, // true
        @SerializedName("healthScore")
        val healthScore: Int?, // 76
        @SerializedName("id")
        val id: Int?, // 716426
        @SerializedName("image")
        val image: String?, // https://spoonacular.com/recipeImages/716426-312x231.jpg
        @SerializedName("imageType")
        val imageType: String?, // jpg
        @SerializedName("license")
        val license: String?, // CC BY-SA 3.0
        @SerializedName("likes")
        val likes: Int?, // 0
        @SerializedName("lowFodmap")
        val lowFodmap: Boolean?, // false
        @SerializedName("missedIngredientCount")
        val missedIngredientCount: Int?, // 8
        @SerializedName("missedIngredients")
        val missedIngredients: List<MissedIngredient?>?,
        @SerializedName("occasions")
        val occasions: List<Any?>?,
        @SerializedName("preparationMinutes")
        val preparationMinutes: Int?, // -1
        @SerializedName("pricePerServing")
        val pricePerServing: Double?, // 112.39
        @SerializedName("readyInMinutes")
        val readyInMinutes: Int?, // 30
        @SerializedName("servings")
        val servings: Int?, // 8
        @SerializedName("sourceName")
        val sourceName: String?, // Full Belly Sisters
        @SerializedName("sourceUrl")
        val sourceUrl: String?, // http://fullbellysisters.blogspot.com/2012/01/cauliflower-fried-rice-more-veggies.html
        @SerializedName("spoonacularSourceUrl")
        val spoonacularSourceUrl: String?, // https://spoonacular.com/cauliflower-brown-rice-and-vegetable-fried-rice-716426
        @SerializedName("summary")
        val summary: String?, // Cauliflower, Brown Rice, and Vegetable Fried Rice might be a good recipe to expand your side dish recipe box. Watching your figure? This gluten free, dairy free, lacto ovo vegetarian, and vegan recipe has <b>192 calories</b>, <b>7g of protein</b>, and <b>6g of fat</b> per serving. For <b>$1.12 per serving</b>, this recipe <b>covers 19%</b> of your daily requirements of vitamins and minerals. This recipe serves 8. This recipe from fullbellysisters.blogspot.com has 3689 fans. This recipe is typical of Chinese cuisine. From preparation to the plate, this recipe takes about <b>30 minutes</b>. Head to the store and pick up peas, broccoli, salt, and a few other things to make it today. Overall, this recipe earns an <b>awesome spoonacular score of 100%</b>. Users who liked this recipe also liked <a href="https://spoonacular.com/recipes/vegetable-fried-brown-rice-36199">Vegetable Fried Brown Rice</a>, <a href="https://spoonacular.com/recipes/vegetable-fried-cauliflower-rice-933261">Vegetable Fried Cauliflower Rice</a>, and <a href="https://spoonacular.com/recipes/easy-vegetable-fried-brown-rice-with-egg-802042">Easy Vegetable Fried Brown Rice with Egg</a>.
        @SerializedName("sustainable")
        val sustainable: Boolean?, // false
        @SerializedName("title")
        val title: String?, // Cauliflower, Brown Rice, and Vegetable Fried Rice
        @SerializedName("unusedIngredients")
        val unusedIngredients: List<Any?>?,
        @SerializedName("usedIngredientCount")
        val usedIngredientCount: Int?, // 0
        @SerializedName("usedIngredients")
        val usedIngredients: List<Any?>?,
        @SerializedName("vegan")
        val vegan: Boolean?, // true
        @SerializedName("vegetarian")
        val vegetarian: Boolean?, // true
        @SerializedName("veryHealthy")
        val veryHealthy: Boolean?, // true
        @SerializedName("veryPopular")
        val veryPopular: Boolean?, // true
        @SerializedName("weightWatcherSmartPoints")
        val weightWatcherSmartPoints: Int? // 4
    ) {
        data class AnalyzedInstruction(
            @SerializedName("name")
            val name: String?,
            @SerializedName("steps")
            val steps: List<Step?>?
        ) {
            data class Step(
                @SerializedName("equipment")
                val equipment: List<Equipment?>?,
                @SerializedName("ingredients")
                val ingredients: List<Ingredient?>?,
                @SerializedName("length")
                val length: Length?,
                @SerializedName("number")
                val number: Int?, // 1
                @SerializedName("step")
                val step: String? // Remove the cauliflower's tough stem and reserve for another use. Using a food processor, pulse cauliflower florets until they resemble rice or couscous. You should end up with around four cups of "cauliflower rice."
            ) {
                data class Equipment(
                    @SerializedName("id")
                    val id: Int?, // 404771
                    @SerializedName("image")
                    val image: String?, // food-processor.png
                    @SerializedName("localizedName")
                    val localizedName: String?, // food processor
                    @SerializedName("name")
                    val name: String? // food processor
                )

                data class Ingredient(
                    @SerializedName("id")
                    val id: Int?, // 10011135
                    @SerializedName("image")
                    val image: String?, // cauliflower.jpg
                    @SerializedName("localizedName")
                    val localizedName: String?, // cauliflower florets
                    @SerializedName("name")
                    val name: String? // cauliflower florets
                )

                data class Length(
                    @SerializedName("number")
                    val number: Int?, // 2
                    @SerializedName("unit")
                    val unit: String? // minutes
                )
            }
        }

        data class ExtendedIngredient(
            @SerializedName("aisle")
            val aisle: String?, // Produce
            @SerializedName("amount")
            val amount: Double?, // 2.0
            @SerializedName("consistency")
            val consistency: String?, // SOLID
            @SerializedName("id")
            val id: Int?, // 11090
            @SerializedName("image")
            val image: String?, // broccoli.jpg
            @SerializedName("measures")
            val measures: Measures?,
            @SerializedName("meta")
            val meta: List<String?>?,
            @SerializedName("name")
            val name: String?, // broccoli
            @SerializedName("nameClean")
            val nameClean: String?, // broccoli
            @SerializedName("original")
            val original: String?, // 2 cups cooked broccoli, chopped small
            @SerializedName("originalName")
            val originalName: String?, // cooked broccoli, chopped small
            @SerializedName("unit")
            val unit: String? // cups
        ) {
            data class Measures(
                @SerializedName("metric")
                val metric: Metric?,
                @SerializedName("us")
                val us: Us?
            ) {
                data class Metric(
                    @SerializedName("amount")
                    val amount: Double?, // 473.176
                    @SerializedName("unitLong")
                    val unitLong: String?, // milliliters
                    @SerializedName("unitShort")
                    val unitShort: String? // ml
                )

                data class Us(
                    @SerializedName("amount")
                    val amount: Double?, // 2.0
                    @SerializedName("unitLong")
                    val unitLong: String?, // cups
                    @SerializedName("unitShort")
                    val unitShort: String? // cups
                )
            }
        }

        data class MissedIngredient(
            @SerializedName("aisle")
            val aisle: String?, // Produce
            @SerializedName("amount")
            val amount: Double?, // 2.0
            @SerializedName("extendedName")
            val extendedName: String?, // cooked broccoli
            @SerializedName("id")
            val id: Int?, // 11090
            @SerializedName("image")
            val image: String?, // https://spoonacular.com/cdn/ingredients_100x100/broccoli.jpg
            @SerializedName("meta")
            val meta: List<String?>?,
            @SerializedName("name")
            val name: String?, // broccoli
            @SerializedName("original")
            val original: String?, // 2 cups cooked broccoli, chopped small
            @SerializedName("originalName")
            val originalName: String?, // cooked broccoli, chopped small
            @SerializedName("unit")
            val unit: String?, // cups
            @SerializedName("unitLong")
            val unitLong: String?, // cups
            @SerializedName("unitShort")
            val unitShort: String? // cup
        )
    }
}