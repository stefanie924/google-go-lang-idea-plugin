package ro.redeul.google.go.lang.psi.impl.types;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ro.redeul.google.go.lang.psi.impl.GoPsiPackagedElementBase;
import ro.redeul.google.go.lang.psi.types.GoPsiType;
import ro.redeul.google.go.lang.psi.types.GoPsiTypeArray;
import ro.redeul.google.go.lang.psi.types.underlying.GoUnderlyingType;
import ro.redeul.google.go.lang.psi.types.underlying.GoUnderlyingTypeArray;
import ro.redeul.google.go.lang.psi.visitors.GoElementVisitor;

/**
 * Author: Toader Mihai Claudiu <mtoader@gmail.com>
 * <p/>
 * Date: Aug 30, 2010
 * Time: 9:07:51 PM
 */
public class GoPsiTypeArrayImpl extends GoPsiPackagedElementBase implements
                                                              GoPsiTypeArray {

    public GoPsiTypeArrayImpl(@NotNull ASTNode node) {
        super(node);
    }

    public GoPsiType getElementType() {
        return findChildByClass(GoPsiType.class);
    }

    @Override
    public void accept(GoElementVisitor visitor) {
        visitor.visitArrayType(this);
    }

    @Override
    public GoUnderlyingType getUnderlyingType() {
        return new GoUnderlyingTypeArray(getElementType().getUnderlyingType(), 10);
    }

    @Override
    public boolean isIdentical(GoPsiType goType) {
        if (!(goType instanceof GoPsiTypeArray)) {
            return false;
        }

        GoPsiTypeArray otherTypeArray = (GoPsiTypeArray) goType;

        // TODO: added check to the length here.
        return getElementType().isIdentical(otherTypeArray.getElementType());
    }

    @NotNull
    @Override
    public String getPresentationText() {
        return String.format("[]%s", getElementType().getPresentationText());
    }
}
