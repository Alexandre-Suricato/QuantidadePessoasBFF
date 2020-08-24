package br.com.zup.quantidadePessoas.bff

import br.com.zup.beagle.core.ServerDrivenComponent
import org.springframework.stereotype.Service
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScreenBuilder
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath
import br.com.zup.beagle.widget.ui.Text
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Alert
import br.com.zup.beagle.widget.action.RequestActionMethod
import br.com.zup.beagle.widget.action.SendRequest
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.navigation.Touchable

data class NumeroDePessoas(
        val qtPessoas: String,
        val pessoas: String
)

@Service
class MyService {
    fun createScreen(): Screen =
            Screen(child = this.createWidget())

    fun createWidget(): Widget = Container(context = ContextData(id = "numeroDePessoas", value = NumeroDePessoas(qtPessoas = "", pessoas = "")),

            children = listOf(
            Container(children = listOf(

                    Image(mode = ImageContentMode.FIT_XY,
                            path = ImagePath.Local.justMobile(mobileId = "background")).applyStyle(Style(size = Size(width = 100.unitPercent(), height = 100.unitPercent()))),
                    Container(children = listOf(

                            Container(children = listOf(
                                    Text(text = "Pessoas: @{numeroDePessoas.qtPessoas}"),

                                    Container(children = listOf(
                                            Touchable(onPress = listOf(
                                                    SetContext(contextId = "numeroDePessoas", path = "qtPessoas", value = "1"),
                                                    SendRequest(url = "/soma/@{numeroDePessoas.pessoas}", method = RequestActionMethod.GET, onSuccess =
                                                    listOf(
                                                            Alert(title = null, message = "@{numeroDePessoas.qtPessoas}"),
                                                            SetContext(contextId = "numeroDePessoas", path = "qtPessoas", value = "@{onSucess.data}")
                                                    ))),
                                                    child = Container(children = listOf(Text(text = "+1"))).applyStyle(Style(margin = EdgeValue(all = 40.unitReal())))),

                                            Touchable(onPress = listOf(
                                                    SetContext(contextId = "numeroDePessoas", path = "qtPessoas", value =  "1"),
                                                    SendRequest(url = "/subtrai/@{numeroDePessoas.pessoas}", method = RequestActionMethod.GET, onSuccess =
                                                    listOf(
                                                            Alert(title = null, message = "@{numeroDePessoas.qtPessoas}"),
                                                            SetContext(contextId = "numeroDePessoas", path = "qtPessoas", value = "@{onSucess.data}")
                                                    ))),
                                                    child = Container(children = listOf(Text(text = "-1"))).applyStyle(Style(margin = EdgeValue(all = 40.unitReal()))))
                                    )).applyFlex(Flex(flexDirection = FlexDirection.ROW)),

                                    Text("@{numeroDePessoas.qtPessoas}")
                            )).applyFlex(Flex(alignContent = AlignContent.CENTER, alignItems = AlignItems.CENTER))


                    )).applyStyle(Style(positionType = PositionType.ABSOLUTE)).applyFlex(Flex(alignContent = AlignContent.CENTER, alignItems = AlignItems.CENTER))
            )).applyStyle(Style(backgroundColor = "#000000")).applyFlex(flex = Flex(grow = 1.0, alignContent = AlignContent.CENTER, alignItems = AlignItems.CENTER, justifyContent = JustifyContent.CENTER))
    ))
}


private class MyScreenBuilder(
        private val component: ServerDrivenComponent
) : ScreenBuilder {
    override fun build() = Screen(child = this.component)
}